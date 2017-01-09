/**
 * Copyright (C) 2016 LibRec
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 * <p>
=======
 * 
>>>>>>> master
=======
 * 
>>>>>>> last minute commit.
 * This file is part of LibRec.
 * LibRec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 * <p>
=======
 *
>>>>>>> master
=======
 *
>>>>>>> last minute commit.
 * LibRec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 * <p>
=======
 *
>>>>>>> master
=======
 *
>>>>>>> last minute commit.
 * You should have received a copy of the GNU General Public License
 * along with LibRec. If not, see <http://www.gnu.org/licenses/>.
 */
package net.librec.eval.rating;

import net.librec.eval.AbstractRecommenderEvaluator;
import net.librec.math.structure.MatrixEntry;
import net.librec.math.structure.SparseMatrix;
import net.librec.recommender.item.RecommendedList;
import net.librec.recommender.item.UserItemRatingEntry;

import java.util.Iterator;

/**
 * MSE: mean square error
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> master
=======
 * 
>>>>>>> last minute commit.
 * @author zhanghaidong and Keqiang Wang
 *
 */
public class MSEEvaluator extends AbstractRecommenderEvaluator {

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    public double evaluate(SparseMatrix testMatrix, RecommendedList recommendedList) {
        if (testMatrix == null || testMatrix.size() == 0) {
            return 0.0;
        }

        double mse = 0.0;

        Iterator<MatrixEntry> testMatrixIter = testMatrix.iterator();
        Iterator<UserItemRatingEntry> recommendedEntryIter = recommendedList.entryIterator();

        while (testMatrixIter.hasNext()) {

            if (recommendedEntryIter.hasNext()) {

                MatrixEntry testMatrixEntry = testMatrixIter.next();
                UserItemRatingEntry userItemRatingEntry = recommendedEntryIter.next();

                if (testMatrixEntry.row() == userItemRatingEntry.getUserIdx()
                        && testMatrixEntry.column() == userItemRatingEntry.getItemIdx()) {

                    double realRating = testMatrixEntry.get();
                    double predictRating = userItemRatingEntry.getValue();
                    mse += Math.pow(realRating - predictRating, 2);

                } else {
                    throw new IndexOutOfBoundsException("index of recommendedList does not equal testMatrix index");
                }

            } else {
                throw new IndexOutOfBoundsException("index size of recommendedList does not equal testMatrix index size");
            }
        }

        return mse / testMatrix.size();
    }
=======
=======
>>>>>>> last minute commit.
	public double evaluate(SparseMatrix testMatrix, RecommendedList recommendedList) {
		if (testMatrix == null||testMatrix.size() == 0 ) {
			return 0.0;
		}

		double mse = 0.0;

		Iterator<MatrixEntry> testMatrixIter = testMatrix.iterator();
		Iterator<UserItemRatingEntry> recommendedEntryIter = recommendedList.entryIterator();

		while (testMatrixIter.hasNext()) {

			if (recommendedEntryIter.hasNext()) {

				MatrixEntry testMatrixEntry = testMatrixIter.next();
				UserItemRatingEntry userItemRatingEntry = recommendedEntryIter.next();

				if (testMatrixEntry.row() == userItemRatingEntry.getUserIdx()
						&& testMatrixEntry.column() == userItemRatingEntry.getItemIdx()) {

					double realRating = testMatrixEntry.get();
					double predictRating = userItemRatingEntry.getValue();
					mse += Math.pow(realRating - predictRating, 2);

				} else {
					throw new IndexOutOfBoundsException("index of recommendedList does not equal testMatrix index");
				}

			} else {
				throw new IndexOutOfBoundsException("index size of recommendedList does not equal testMatrix index size");
			}
		}

		return mse / testMatrix.size();
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}
