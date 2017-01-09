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
package net.librec.recommender;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.BiMap;

import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.DataModel;
import net.librec.eval.Measure;
import net.librec.eval.Measure.MeasureValue;
import net.librec.eval.RecommenderEvaluator;
import net.librec.math.structure.MatrixEntry;
import net.librec.math.structure.SparseMatrix;
import net.librec.recommender.item.GenericRecommendedItem;
import net.librec.recommender.item.RecommendedItem;
import net.librec.recommender.item.RecommendedItemList;
import net.librec.recommender.item.RecommendedList;
import net.librec.recommender.item.UserItemRatingEntry;
import net.librec.util.ModelDataUtil;
import net.librec.util.ReflectionUtil;

/**
 * Abstract Recommender Methods
 *
 * @author WangYuFeng and Wang Keqiang
 */
public abstract class AbstractRecommender implements Recommender {
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    /**
     * LOG
     */
    protected final Log LOG = LogFactory.getLog(this.getClass());

=======
=======
>>>>>>> last minute commit.
	/**
	 * LOG
	 */
	protected final Log LOG = LogFactory.getLog(this.getClass());
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * is ranking or rating
     */
    protected boolean isRanking;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * topN
     */
    protected int topN;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * conf
     */
    protected Configuration conf;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * RecommenderContext
     */
    protected RecommenderContext context;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * trainMatrix
     */
    protected SparseMatrix trainMatrix;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * testMatrix
     */
    protected SparseMatrix testMatrix;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * validMatrix
     */
    protected SparseMatrix validMatrix;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * Recommended Item List
     */
    protected RecommendedList recommendedList;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * the number of users
     */
    protected int numUsers;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * the number of items
     */
    protected int numItems;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * the number of rates
     */
    protected int numRates;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * Maximum rate of rating scale
     */
    protected double maxRate;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
    /**
     * Minimum rate of rating scale
     */
    protected double minRate;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD

    /**
     * a list of rating scales
     */
    protected static List<Double> ratingScale;

    /**
     * user Mapping Data
     */
    public BiMap<String, Integer> userMappingData;

    /**
     * item Mapping Data
     */
    public BiMap<String, Integer> itemMappingData;
=======
=======
>>>>>>> last minute commit.
    // a list of rating scales
    protected static List<Double> ratingScale;
    /**
     * user Mapping Data
     */
	public BiMap<String, Integer> userMappingData;
    /**
     * item Mapping Data
     */
	public BiMap<String, Integer> itemMappingData;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

    /**
     * global mean of ratings
     */
    protected double globalMean;

    /**
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
     * early-stop criteria
     */
    protected boolean earlyStop;

    /**
     * verbose
     */
    protected static boolean verbose = true;

    /**
     * objective loss
     */
    protected double loss, lastLoss = 0.0d;

    /**
     * whether to adjust learning rate automatically
     */
    protected boolean isBoldDriver;

    /**
     * decay of learning rate
     */
    protected float decay;

    /**
     * setup
     *
     * @throws LibrecException
     * @throws FileNotFoundException
=======
=======
>>>>>>> last minute commit.
     * setup
     *
     * @throws LibrecException
     * @throws FileNotFoundException 
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
     */
    protected void setup() throws LibrecException {
        conf = context.getConf();
        isRanking = conf.getBoolean("rec.recommender.isranking");
        if (isRanking) {
<<<<<<< HEAD
            topN = conf.getInt("rec.recommender.ranking.topN", 5);
        }

        earlyStop = conf.getBoolean("rec.recommender.earlystop", false);
        verbose = conf.getBoolean("rec.recommender.verbose", true);

        trainMatrix = (SparseMatrix) getDataModel().getTrainDataSet();
        testMatrix = (SparseMatrix) getDataModel().getTestDataSet();
        validMatrix = (SparseMatrix) getDataModel().getValidDataSet();
        userMappingData = getDataModel().getUserMappingData();
        itemMappingData = getDataModel().getItemMappingData();

=======
=======
     */
    protected void setup() throws LibrecException {
        conf = context.getConf();
        isRanking = conf.getBoolean("rec.recommender.isranking");
        if (isRanking) {
>>>>>>> last minute commit.
            topN = conf.getInt("rec.recommender.ranking.topn", 5);
        }
        trainMatrix = getDataModel().getDataSplitter().getTrainData();
        testMatrix = getDataModel().getDataSplitter().getTestData();
        validMatrix = getDataModel().getDataSplitter().getValidData();
        userMappingData = getDataModel().getUserMappingData();
        itemMappingData = getDataModel().getItemMappingData();
        
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
        numUsers = trainMatrix.numRows();
        numItems = trainMatrix.numColumns();
        numRates = trainMatrix.size();
        ratingScale = new ArrayList<>(trainMatrix.getValueSet());
        Collections.sort(ratingScale);
        maxRate = Collections.max(trainMatrix.getValueSet());
        minRate = Collections.min(trainMatrix.getValueSet());
        globalMean = trainMatrix.size() / numRates;
    }

    /**
     * train Model
     *
     * @throws LibrecException
     */
    protected abstract void trainModel() throws LibrecException;

    /**
     * recommend
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
     *
     * @param context
     * @throws LibrecException
     */
    public void recommend(RecommenderContext context) throws LibrecException {
        this.context = context;
        setup();
        LOG.info("Job Setup completed.");
        trainModel();
        LOG.info("Job Train completed.");
        this.recommendedList = recommend();
        LOG.info("Job End.");
        cleanup();
    }

    /**
     * recommend
=======
>>>>>>> master
=======
>>>>>>> last minute commit.
     * * predict the ranking scores or ratings in the test data
     *
     * @return predictive ranking score or rating matrix
     * @throws LibrecException
     */
    protected RecommendedList recommend() throws LibrecException {
        if (isRanking && topN > 0) {
            recommendedList = recommendRank();
        } else {
            recommendedList = recommendRating();
        }
        return recommendedList;
    }

    /**
     * recommend
     * * predict the ranking scores in the test data
     *
     * @return predictive rating matrix
     * @throws LibrecException
     */
    protected RecommendedList recommendRank() throws LibrecException {
        recommendedList = new RecommendedItemList(numUsers - 1, numUsers);

        for (int userIdx = 0; userIdx < numUsers; ++userIdx) {
            Set<Integer> itemSet = trainMatrix.getColumnsSet(userIdx);
            for (int itemIdx = 0; itemIdx < numItems; ++itemIdx) {
                if (itemSet.contains(itemIdx)) {
                    continue;
                }
                double predictRating = predict(userIdx, itemIdx);
                if (Double.isNaN(predictRating)) {
                    continue;
                }
                recommendedList.addUserItemIdx(userIdx, itemIdx, predictRating);
            }
            recommendedList.topNRankItemsByUser(userIdx, topN);

        }
        return recommendedList;
    }

    /**
     * recommend
     * * predict the ratings in the test data
     *
     * @return predictive rating matrix
     * @throws LibrecException
     */
    protected RecommendedList recommendRating() throws LibrecException {
        recommendedList = new RecommendedItemList(numUsers - 1, numUsers);

        for (MatrixEntry matrixEntry : testMatrix) {
            int userIdx = matrixEntry.row();
            int itemIdx = matrixEntry.column();
            double predictRating = predict(userIdx, itemIdx, true);
            if (Double.isNaN(predictRating)) {
                predictRating = globalMean;
            }
            recommendedList.addUserItemIdx(userIdx, itemIdx, predictRating);
        }

        return recommendedList;
    }

    /**
     * predict a specific rating for user userIdx on item itemIdx, note that the
     * prediction is not bounded. It is useful for building models with no need
     * to bound predictions.
     *
     * @param userIdx user index
     * @param itemIdx item index
     * @return predictive rating for user userIdx on item itemIdx without bound
     * @throws LibrecException
     */
    protected abstract double predict(int userIdx, int itemIdx) throws LibrecException;


    /**
     * predict a specific rating for user userIdx on item itemIdx. It is useful for evalution which requires predictions are
     * bounded.
     *
     * @param userIdx user index
     * @param itemIdx item index
     * @return predictive rating for user userIdx on item itemIdx with bound
     * @throws LibrecException
     */
    protected double predict(int userIdx, int itemIdx, boolean bound) throws LibrecException {
        double predictRating = predict(userIdx, itemIdx);

        if (bound) {
            if (predictRating > maxRate) {
                predictRating = maxRate;
            } else if (predictRating < minRate) {
                predictRating = minRate;
            }
        }

        return predictRating;
    }

    /**
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
=======
=======
>>>>>>> last minute commit.
     * recommend
     *
     * @param context
     * @throws LibrecException
     */
    public void recommend(RecommenderContext context) throws LibrecException {
        this.context = context;
        setup();
        LOG.info("Job Setup completed.");
        trainModel();
        LOG.info("Job Train completed.");
        this.recommendedList = recommend();
        LOG.info("Job End.");
        cleanup();
    }

    /**
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
     * evaluate
     *
     * @param evaluator
     */
    public double evaluate(RecommenderEvaluator evaluator) throws LibrecException {
        evaluator.setTopN(this.topN);
        return evaluator.evaluate(context, recommendedList);
    }

    /**
     * evaluate Map
     *
     * @return
     * @throws LibrecException
     */
    public Map<MeasureValue, Double> evaluateMap() throws LibrecException {
        Map<MeasureValue, Double> evaluatedMap = new HashMap<>();
        List<MeasureValue> measureValueList = Measure.getMeasureEnumList(isRanking, topN);
        if (measureValueList != null) {
            for (MeasureValue measureValue : measureValueList) {
                RecommenderEvaluator evaluator = ReflectionUtil
                        .newInstance(measureValue.getMeasure().getEvaluatorClass());
                if (isRanking && measureValue.getTopN() != null && measureValue.getTopN() > 0) {
                    evaluator.setTopN(measureValue.getTopN());
                }
                double evaluatedValue = evaluator.evaluate(context, recommendedList);
                evaluatedMap.put(measureValue, evaluatedValue);
            }
        }
        return evaluatedMap;
    }

    /**
     * cleanup
     *
     * @throws LibrecException
     */
    protected void cleanup() throws LibrecException {

    }

    /*
     * (non-Javadoc)
     *
     * @see net.librec.recommender.Recommender#loadModel()
     */
    @Override
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    public void loadModel(String filePath) {
        ModelDataUtil.loadRecommenderModel(this, filePath);
=======
    public void loadModel() {
        String filePath = conf.get("");
//        ModelDataUtil.loadRecommenderModel(this, filePath);
>>>>>>> master
=======
    public void loadModel() {
        String filePath = conf.get("");
//        ModelDataUtil.loadRecommenderModel(this, filePath);
>>>>>>> last minute commit.
    }

    /*
     * (non-Javadoc)
     *
     * @see net.librec.recommender.Recommender#saveModel()
     */
    @Override
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    public void saveModel(String filePath) {
        ModelDataUtil.saveRecommenderModel(this, filePath);
=======
    public void saveModel() {
        String filePath = conf.get("");
//        ModelDataUtil.saveRecommenderModel(this, filePath);
>>>>>>> master
=======
    public void saveModel() {
        String filePath = conf.get("");
//        ModelDataUtil.saveRecommenderModel(this, filePath);
>>>>>>> last minute commit.
    }

    /**
     * get Context
     *
     * @return
     */
    protected RecommenderContext getContext() {
        return context;
    }

    /**
     * set Context
     *
     * @param context
     */
    public void setContext(RecommenderContext context) {
        this.context = context;
    }

    /**
     * get Data Model
     */
    public DataModel getDataModel() {
        return context.getDataModel();
    }

    /**
     * get Recommended List
     */
    public List<RecommendedItem> getRecommendedList() {
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
        if (recommendedList != null && recommendedList.size() > 0) {
            List<RecommendedItem> userItemList = new ArrayList<>();
            Iterator<UserItemRatingEntry> recommendedEntryIter = recommendedList.entryIterator();
            if (userMappingData != null && userMappingData.size() > 0 && itemMappingData != null && itemMappingData.size() > 0) {
                BiMap<Integer, String> userMappingInverse = userMappingData.inverse();
                BiMap<Integer, String> itemMappingInverse = itemMappingData.inverse();
                while (recommendedEntryIter.hasNext()) {
                    UserItemRatingEntry userItemRatingEntry = recommendedEntryIter.next();
                    if (userItemRatingEntry != null) {
                        String userId = userMappingInverse.get(userItemRatingEntry.getUserIdx());
                        String itemId = itemMappingInverse.get(userItemRatingEntry.getItemIdx());
                        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(itemId)) {
                            userItemList.add(new GenericRecommendedItem(userId, itemId, userItemRatingEntry.getValue()));
                        }
                    }
                }
                return userItemList;
            }
        }
        return null;
    }

    /**
     * Post each iteration, we do things:
     * <p>
     * <ol>
     * <li>print debug information</li>
     * <li>check if converged</li>
     * <li>if not, adjust learning rate</li>
     * </ol>
     *
     * @param iter current iteration
     * @return boolean: true if it is converged; false otherwise
     */
    protected boolean isConverged(int iter) {
        return false;
    }
=======
=======
>>>>>>> last minute commit.
    	if (recommendedList != null && recommendedList.size() > 0) {
    		List<RecommendedItem> userItemList = new ArrayList<>();
    		Iterator<UserItemRatingEntry> recommendedEntryIter = recommendedList.entryIterator();
    		if (userMappingData != null && userMappingData.size() > 0 && itemMappingData != null && itemMappingData.size() > 0) {
    			BiMap<Integer, String> userMappingInverse = userMappingData.inverse();
    			BiMap<Integer, String> itemMappingInverse = itemMappingData.inverse();
    			while (recommendedEntryIter.hasNext()) {
    				UserItemRatingEntry userItemRatingEntry = recommendedEntryIter.next();
    				if (userItemRatingEntry != null) {
    					String userId = userMappingInverse.get(userItemRatingEntry.getItemIdx());
    					String itemId = itemMappingInverse.get(userItemRatingEntry.getItemIdx());
    					if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(itemId)) {
    						userItemList.add(new GenericRecommendedItem(userId, itemId, userItemRatingEntry.getValue()));
						}
					}
				}
    			return userItemList;
			}
		}
        return null;
    }

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}
