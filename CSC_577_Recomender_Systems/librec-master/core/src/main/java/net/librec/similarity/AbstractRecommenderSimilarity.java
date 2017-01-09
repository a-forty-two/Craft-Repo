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
package net.librec.similarity;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import net.librec.conf.Configured;
import net.librec.data.DataModel;
import net.librec.data.convertor.feature.SocialDataFeature;
=======
=======
>>>>>>> last minute commit.
import java.util.ArrayList;
import java.util.List;

import net.librec.data.DataModel;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
import net.librec.math.structure.SparseMatrix;
import net.librec.math.structure.SparseVector;
import net.librec.math.structure.SymmMatrix;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

/**
 * Calculate Recommender Similarity, such as cosine, Pearson, Jaccard
 * similarity, etc.
 *
=======
/**
 * Calculate Recommender Similarity, such as cosine, Pearson, Jaccard similarity, etc.
 * 
>>>>>>> master
=======
/**
 * Calculate Recommender Similarity, such as cosine, Pearson, Jaccard similarity, etc.
 * 
>>>>>>> last minute commit.
 * @author zhanghaidong
 *
 */

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
public abstract class AbstractRecommenderSimilarity extends Configured implements RecommenderSimilarity {
    /**
     * Similarity Matrix
     */
    protected SymmMatrix similarityMatrix;

    /**
     * social Similarity Matrix
     */
    protected SymmMatrix socialSimilarityMatrix;

    /**
     *
     * @param dataModel
     * @param isUser:
     *            calculate the similarity between users, or the similarity
     *            between items.
     */
    @Override
    public void buildSimilarityMatrix(DataModel dataModel, boolean isUser) {
        SparseMatrix trainMatrix = dataModel.getDataSplitter().getTrainData();
        int numUsers = trainMatrix.numRows();
        int numItems = trainMatrix.numColumns();
        int count = isUser ? numUsers : numItems;

        similarityMatrix = new SymmMatrix(count);

        for (int i = 0; i < count; i++) {
            SparseVector thisVector = isUser ? trainMatrix.row(i) : trainMatrix.column(i);
            if (thisVector.getCount() == 0) {
                continue;
            }
            // user/item itself exclusive
            for (int j = i + 1; j < count; j++) {
                SparseVector thatVector = isUser ? trainMatrix.row(j) : trainMatrix.column(j);
                if (thatVector.getCount() == 0) {
                    continue;
                }

                double sim = getCorrelation(thisVector, thatVector);
                if (!Double.isNaN(sim)) {
                    similarityMatrix.set(i, j, sim);
                }
            }
        }
    }

    /**
     *
     * @param dataModel
     */
    @Override
    public void buildSocialSimilarityMatrix(DataModel dataModel) {
        SparseMatrix trainMatrix = dataModel.getDataSplitter().getTrainData();
        SparseMatrix socialMatrix = ((SocialDataFeature) dataModel.getDataFeature()).getUserFeature();
        int numUsers = trainMatrix.numRows();

        socialSimilarityMatrix = new SymmMatrix(numUsers);

        for (int userIdx = 0; userIdx < numUsers; userIdx++) {
            SparseVector userVector = trainMatrix.row(userIdx);
            if (userVector.getCount() == 0) {
                continue;
            }
            List<Integer> socialList = socialMatrix.getRows(userIdx);
            for (int socialIdx : socialList) {
                SparseVector socialVector = trainMatrix.row(socialIdx);
                if (socialVector.getCount() == 0) {
                    continue;
                }

                double sim = getCorrelation(userVector, socialVector);
                if (!Double.isNaN(sim)) {
                    socialSimilarityMatrix.set(userIdx, socialIdx, sim);
                }
            }
        }
    }

    /**
     * Find the common rated items by this user and that user, or the common
     * users have rated this item or that item. And then return the similarity.
     *
     * @param thisVector:
     *            the rated items by this user, or users that have rated this
     *            item .
     * @param thatVector:
     *            the rated items by that user, or users that have rated that
     *            item.
     * @return
     */
    public double getCorrelation(SparseVector thisVector, SparseVector thatVector) {
        // compute similarity
        List<Double> thisList = new ArrayList<Double>();
        List<Double> thatList = new ArrayList<Double>();

        for (Integer idx : thatVector.getIndex()) {
            if (thisVector.contains(idx)) {
                thisList.add(thisVector.get(idx));
                thatList.add(thatVector.get(idx));
            }
        }
        double sim = getSimilarity(thisList, thatList);

        // shrink to account for vector size
        if (!Double.isNaN(sim)) {
            int n = thisList.size();
            int shrinkage = conf.getInt("rec.similarity.shrinkage", 0);
            if (shrinkage > 0)
                sim *= n / (n + shrinkage + 0.0);
        }

        return sim;
    }

    /**
     * calculate the similarity between thisList and thatList
     *
     * @param thisList
     * @param thatList
     * @return
     */
    protected abstract double getSimilarity(List<? extends Number> thisList, List<? extends Number> thatList);

    /**
     * return the similarity matrix.
     */
    @Override
    public SymmMatrix getSimilarityMatrix() {
        return similarityMatrix;
    }

    /**
     * return the social similarity matrix.
     */
    @Override
    public SymmMatrix getSocialSimilarityMatrix() {
        return socialSimilarityMatrix;
    }
=======
=======
>>>>>>> last minute commit.
public abstract class AbstractRecommenderSimilarity implements RecommenderSimilarity {
	/**
	 * Similarity Matrix
	 */
	protected SymmMatrix similarityMatrix;
	
	/**
	 * 
	 * @param dataModel
	 * @param isUser: calculate the similarity between users, or the similarity between items.
	 */
	public void buildSimilarityMatrix(DataModel dataModel, boolean isUser) {
		SparseMatrix trainMatrix = dataModel.getDataSplitter().getTrainData();
		int numUsers = trainMatrix.numRows();
		int numItems = trainMatrix.numColumns();
		int count = isUser ? numUsers : numItems;
		
		similarityMatrix = new SymmMatrix(count);
		
		for (int i = 0; i < count; i++) {
			SparseVector thisVector = isUser ? trainMatrix.row(i) : trainMatrix.column(i);
			if (thisVector.getCount() == 0) {
				continue;
			}
			// user/item itself exclusive
			for (int j = i + 1; j < count; j++) {
				SparseVector thatVector = isUser ? trainMatrix.row(j) : trainMatrix.column(j);
				if (thatVector.getCount() == 0) {
					continue;
				}
				
				double sim = getCorrelation(thisVector, thatVector);
				if (!Double.isNaN(sim)) {
					similarityMatrix.set(i, j, sim);
				}
			}
		}
	}
	
	/**
	 * Find the common rated items by this user and that user, 
	 * or the common users have rated this item or that item. And then return the similarity. 
	 * @param thisVector: the rated items by this user, or users that have rated this item . 
	 * @param thatVector: the rated items by that user, or users that have rated that item.
	 * @return
	 */
	public double getCorrelation(SparseVector thisVector, SparseVector thatVector) {
		// compute similarity
		List<Double> thisList = new ArrayList<Double>();
		List<Double> thatList = new ArrayList<Double>();

		for (Integer idx : thatVector.getIndex()) {
			if (thisVector.contains(idx)) {
				thisList.add(thisVector.get(idx));
				thatList.add(thatVector.get(idx));
			}
		}
		double sim = getSimilarity(thisList, thatList);
		return sim;
	}
	
	/**
	 * calculate the similarity between thisList and thatList
	 * @param thisList
	 * @param thatList
	 * @return
	 */
	protected abstract double getSimilarity(List<? extends Number> thisList, List<? extends Number> thatList);
	
	
	/**
	 * return the similarity matrix.
	 */
	public SymmMatrix getSimilarityMatrix() {
		return similarityMatrix;
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}
