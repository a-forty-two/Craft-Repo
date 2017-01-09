/**
 * Copyright (C) 2016 LibRec
 * <p>
 * This file is part of LibRec.
 * LibRec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * LibRec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with LibRec. If not, see <http://www.gnu.org/licenses/>.
 */
package net.librec.recommender.cf.ranking;

import net.librec.annotation.ModelData;
import net.librec.common.LibrecException;
import net.librec.math.algorithm.Maths;
import net.librec.math.structure.DenseMatrix;
import net.librec.math.structure.SparseVector;
import net.librec.recommender.MatrixFactorizationRecommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shi et al., <strong>Climf: learning to maximize reciprocal rank with collaborative less-is-more filtering.</strong>,
 * RecSys 2012.
 */
@ModelData({"isRanking", "climf", "P", "Q"})
public class CLIMFRecommender extends MatrixFactorizationRecommender {

    private float learnRate;
    /**
     * user and item latent factors
     */
    private DenseMatrix P, Q;

    @Override
    protected void setup() throws LibrecException {
        super.setup();
    }

    @Override
    protected void trainModel() throws LibrecException {

        double loss = 0.0f;
        P = userFactors;
        Q = itemFactors;

        for (int iter = 1; iter <= numIterations; iter++) {

            loss = 0.0f;
            for (int u = 0; u < numUsers; u++) {

                SparseVector uv = trainMatrix.row(u);
                double[] sgds = new double[numFactors];

                for (int f = 0; f < numFactors; f++) {

                    double sgd = -regUser * P.get(u, f);

                    for (int j : uv.getIndex()) {
                        double fuj = predict(u, j);
                        double qjf = Q.get(j, f);

                        sgd += Maths.logistic(-fuj) * qjf;

                        for (int k : uv.getIndex()) {
                            if (k == j) {
                                continue;
                            }

                            double fuk = predict(u, k);
                            double qkf = Q.get(k, f);

                            double x = fuk - fuj;

                            sgd += Maths.logisticGradientValue(x) / (1 - Maths.logistic(x)) * (qjf - qkf);
                        }
                    }

                    sgds[f] = sgd;
                }

                Map<Integer, List<Double>> itemSgds = new HashMap<>();
                for (int j : uv.getIndex()) {
                    double fuj = predict(u, j);
                    List<Double> jSgds = new ArrayList<>();
                    for (int f = 0; f < numFactors; f++) {
                        double puf = P.get(u, f);
                        double qjf = Q.get(j, f);

                        double yuj = uv.contains(j) ? 1.0 : 0.0;
                        double sgd = yuj * Maths.logistic(-fuj) * puf - regItem * qjf;

                        for (int k : uv.getIndex()) {
                            if (k == j) {
                                continue;
                            }

                            double fuk = predict(u, k);
                            double x = fuk - fuj;

                            sgd += Maths.logisticGradientValue(-x) * (1.0 / (1 - Maths.logistic(x)) - 1.0 / (1 - Maths.logistic(-x))) * puf;
                        }

                        jSgds.add(sgd);
                    }

                    itemSgds.put(j, jSgds);
                }

                for (int f = 0; f < numFactors; f++) {
                    P.add(u, f, learnRate * sgds[f]);
                }

                for (int j = 0; j < numItems; j++) {
                    List<Double> jSgds = itemSgds.get(j);
                    for (int f = 0; f < numFactors; f++) {
                        if (null != jSgds) {
                            Q.add(j, f, learnRate * jSgds.get(f));
                        }
                    }
                }

                for (int j = 0; j < numItems; j++) {
                    if (uv.contains(j)) {
                        double fuj = predict(u, j);

                        loss += Math.log(Maths.logistic(fuj));

                        for (int k : uv.getIndex()) {
                            double fuk = predict(u, k);
                            loss += Math.log(1 - Maths.logistic(fuk - fuj));
                        }
                    }

                    for (int f = 0; f < numFactors; f++) {
                        double puf = P.get(u, f);
                        double qjf = Q.get(j, f);

                        loss += -0.5 * (regUser * puf * puf + regItem * qjf * qjf);
                        ;
                    }
                }
            }

            if (isConverged(iter) && earlyStop) {
                break;
            }
        }
    }

}
