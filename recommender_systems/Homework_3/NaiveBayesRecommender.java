package net.librec.recommender;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

import com.google.common.collect.*;
import net.librec.annotation.ModelData;
import net.librec.common.LibrecException;
import net.librec.math.structure.*;


@ModelData({"isRanking", "trainMatrix"})
public class NaiveBayesRecommender extends AbstractRecommender
{

    private static final int BSIZE = 1024 * 1024;

    protected SparseMatrix m_featureMatrix;
    protected double m_threshold;
    protected DenseMatrix featureLogOdds;
    protected double [] userBias;

    @Override
    public void setup() throws LibrecException {
        // out of mercy this method was given
        super.setup();

        // we use this to set the threshold of what is a liked
        m_threshold = conf.getDouble("rec.rating.threshold");

        String contentPath = conf.get("dfs.content.path");
        Table<Integer, Integer, Integer> contentTable = HashBasedTable.create();
        HashBiMap<String, Integer> itemIds = HashBiMap.create();
        HashBiMap<String, Integer> featureIds = HashBiMap.create();

        try {

            FileInputStream fileInputStream = new FileInputStream(contentPath);
            FileChannel fileRead = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
            int len;
            String bufferLine = new String();
            byte[] bytes = new byte[BSIZE];
            while ((len = fileRead.read(buffer)) != -1) {
                buffer.flip();
                buffer.get(bytes, 0, len);
                bufferLine = bufferLine.concat(new String(bytes, 0, len));
                String[] bufferData = bufferLine.split(System.getProperty("line.separator") + "+");
                boolean isComplete = bufferLine.endsWith(System.getProperty("line.separator"));
                int loopLength = isComplete ? bufferData.length : bufferData.length - 1;
                for (int i = 0; i < loopLength; i++) {
                    String line = new String(bufferData[i]);
                    String[] data = line.trim().split("[ \t,]+");

                    String item = data[0];
                    // inner id starting from 0
                    int row = itemIds.containsKey(item) ? itemIds.get(item) : itemIds.size();
                    itemIds.put(item, row);

                    for (int j = 1; j < data.length; j++) {
                        String feature = data[j];
                        int col = featureIds.containsKey(feature) ? featureIds.get(feature) : featureIds.size();
                        featureIds.put(feature, col);

                        contentTable.put(row, col, 1);
                    }

                }
            }
        } catch (IOException e) {
            LOG.error("Error reading file: " + contentPath + e);
            throw (new LibrecException(e));
        }

        m_featureMatrix = new SparseMatrix(itemIds.size(), featureIds.size(), contentTable);
        LOG.info("Loaded item features from " + contentPath);
    }


    public void trainModel () {

        // number of users in the data and number of features in the
        // data, U x F matrix
        int numUsers = trainMatrix.numRows();
        int numFeatures = m_featureMatrix.numColumns();

        // these are used to say if the user liked
        // or disliked the items
        int itemsLikes;
        int itemsDislike;

        // likes and dislikes for each feature
        int[] featuresLikes = new int[numFeatures];
        int[] featuresDislike = new int[numFeatures];

        // initialize the hashmaps to 0
        Arrays.fill(featuresLikes, new Integer(0));
        Arrays.fill(featuresDislike, new Integer(0));

        // matrix that has the log odds for each feature
        featureLogOdds = new DenseMatrix(numUsers, numFeatures);

        userBias = new double[numUsers];

        for (int row = 0; row < numUsers; row++) {

            // likes for the features
            itemsLikes = 0;
            itemsDislike = 0;

            // this get's the user and how they rated the items
            SparseVector itemVector = trainMatrix.row(row);
            Iterator<VectorEntry> item_ir = itemVector.iterator();

            while (item_ir.hasNext()) {

                VectorEntry ratingEntry = item_ir.next();
                double rating = ratingEntry.get();
                int item = ratingEntry.index();

                // if the rating is greater then the threshold then
                // we add it to the toal ratings
                if (rating > m_threshold) {
                    itemsLikes++;
                } else if (rating > 0) {
                    itemsDislike++;
                }

                SparseVector featureVector = m_featureMatrix.row(item);
                Iterator<VectorEntry> feature_ir = featureVector.iterator();

                while (feature_ir.hasNext()) {

                    VectorEntry featureEntry = feature_ir.next();
                    int feature = featureEntry.index();

                    if (rating > m_threshold) {
                        featuresLikes[feature]++;
                    } else if (rating > 0) {
                        featuresDislike[feature]++;
                    }
                }


                // user bias = p(L) / p(DL)
                double total_ratings = itemsLikes + itemsDislike;
                double prob_like = (double) itemsLikes / total_ratings;
                double prob_dislike = (double) itemsDislike / total_ratings;
                userBias[row] = prob_like / prob_dislike;


                for (int f = 0; f < numFeatures; f++) {

                    // get the probability of the feature
                    // p(f)
                    //System.out.println(row);
                    //System.out.println(f);
                    double prob_feature = (double)(featuresLikes[f] + featuresDislike[f] + 1) / (total_ratings + 1);


                    // get the probability of the feature given like and dislike
                    // p(f|l) and p(f|dl)
                    double prob_feature_like = (double)(featuresLikes[f] + 1) / (itemsLikes + 1);
                    double prob_feature_dislike = (double)(featuresDislike[f] + 1) / (itemsDislike + 1);


                    // get the probability of like and dislike given feature
                    // p(l|f) and p(dl|f)
                    double prob = (prob_feature_like * prob_feature + 1) / (prob_feature_dislike * prob_feature + 1);
                    featureLogOdds.set(row, f, Math.log(prob));

                }
            }
        }
    }


    public double predict (int user, int item) {

        double aggregate_feature_log_odds = 0;
        SparseVector featureVector = m_featureMatrix.row(item);
        Iterator<VectorEntry> featureIter = featureVector.iterator();

        while (featureIter.hasNext()) {

            VectorEntry featureEntry = featureIter.next();
            int feature = featureEntry.index();
            aggregate_feature_log_odds += featureLogOdds.get(user, feature);
        }

        aggregate_feature_log_odds += userBias[user];

        double probLiked = Math.exp(aggregate_feature_log_odds) / (Math.exp(aggregate_feature_log_odds) + 1);
        double range = maxRate - minRate;
        double pred = (probLiked * range) + minRate;
        return pred;

    }


}
