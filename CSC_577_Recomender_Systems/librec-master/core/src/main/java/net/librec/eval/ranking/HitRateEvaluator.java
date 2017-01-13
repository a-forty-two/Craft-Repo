package net.librec.eval.ranking;

import net.librec.eval.AbstractRecommenderEvaluator;
import net.librec.math.structure.SparseMatrix;
import net.librec.recommender.item.ItemEntry;
import net.librec.recommender.item.RecommendedList;

import java.util.ArrayList;
import java.util.List;

/**
 * HitRateEvaluator
 * <p>
 * Xia Ning and George Karypis, <strong>SLIM: Sparse Linear Methods for Top-N Recommender Systems</strong>, ICDM 2011. <br>
 * <p>
 * They apply a leave-one-out validation method to evaluate the algorithm performance. In each run, each of the datasets
 * is split into a training set and a testing set by randomly selecting one of the non-zero entries of each user and
 * placing it into the testing set.
 */

public class HitRateEvaluator extends AbstractRecommenderEvaluator {

    public double evaluate(SparseMatrix testMatrix, RecommendedList recommendedList) {

        if (testMatrix.size() == 0) {
            return 0.0;
        }

        int totalHits = 0;
        int numUsers = testMatrix.numRows();
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
        int nonZeroNumUsers = 0;
        for (int userID = 0; userID < numUsers; userID++) {
            List<Integer> testListByUser = testMatrix.getColumns(userID);
            if (testListByUser.size() > 0) {
                List<ItemEntry<Integer, Double>> recommendArrayListByUser = recommendedList.getItemIdxListByUserIdx(userID);

                List<Integer> recommendListByUser = arrayListToList(recommendArrayListByUser);

                if (recommendListByUser.contains(testListByUser.get(0))) {
                    totalHits++;
                }
                nonZeroNumUsers++;
            }
        }

        return totalHits / (nonZeroNumUsers + 0.0);
=======
=======
>>>>>>> last minute commit.
        for (int userID = 0; userID < numUsers; userID++) {
            List<Integer> testListByUser = testMatrix.getColumns(userID);
            List<ItemEntry<Integer, Double>> recommendArrayListByUser = recommendedList.getItemIdxListByUserIdx(userID);

            List<Integer> recommendListByUser = arrayListToList(recommendArrayListByUser);

            if (recommendListByUser.contains(testListByUser.get(0))) {
                totalHits++;
            }
        }

        return totalHits / (numUsers + 0.0);
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
    }

    private List<Integer> arrayListToList(List<ItemEntry<Integer, Double>> recommendArrayListByUser) {

        List<Integer> recommendListByUser = new ArrayList<>();

        for (ItemEntry<Integer, Double> item : recommendArrayListByUser) {
            recommendListByUser.add(item.getKey());
        }

        return recommendListByUser;
    }
}