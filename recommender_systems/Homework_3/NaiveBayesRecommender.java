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
    protected DenseMatrix m_featureLogOdds;
    protected double [] m_userBias;

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

        int userSize = trainMatrix.numRows(); // trainMatrix = U x F matrix
        int featureSize = m_featureMatrix.numColumns(); // trainMatrix = U x F matrix

        int positiveVotes;
        int negativeVotes;
        int [] positiveCount = new int[featureSize];
        int [] negativeCount = new int[featureSize];
        Arrays.fill(positiveCount,new Integer(0));
        Arrays.fill(negativeCount,new Integer(0));
        m_featureLogOdds = new DenseMatrix(userSize, featureSize);
        m_userBias = new double[userSize];

        for (int row = 0; row < userSize; row++) {

            positiveVotes = 0;
            negativeVotes = 0;

            SparseVector itemVector = trainMatrix.row(row);
            Iterator<VectorEntry> itemIter = itemVector.iterator();


            while (itemIter.hasNext()) {

                VectorEntry ratingEntry = itemIter.next(); // sparse vector

                double rating = ratingEntry.get();
                int item = ratingEntry.index();


                if (rating > m_threshold) {
                    positiveVotes++;
                } else if (rating > 0) {
                    negativeVotes++;
                }

                SparseVector featureVector = m_featureMatrix.row(item);
                Iterator<VectorEntry> featureIter = featureVector.iterator();


                while (featureIter.hasNext()) {


                    VectorEntry featureEntry = featureIter.next();
                    int feature = featureEntry.index();

                    if (rating > m_threshold) {
                        positiveCount[feature]++;
                    } else if (rating > 0) {
                        negativeCount[feature]++;
                    }

                }

            }

            positiveVotes++;
            negativeVotes++;
            m_userBias[row] = Math.log(positiveVotes/negativeVotes);

            for (int f = 0; f < featureSize; f++) {
                double probPos = ((double) positiveCount[f]+1) / positiveVotes;
                double probNeg = ((double) negativeCount[f]+1) / negativeVotes;

                m_featureLogOdds.set(row, f, Math.log(probPos/probNeg));
            }
        }
    }


    public double predict (int user, int item) {
        double logOdds = 0;


        for (int f = 0; f < m_featureMatrix.numColumns(); f++) {
            logOdds += m_featureLogOdds.get(user, f);
        }
        logOdds += m_userBias[user];

        double probLiked = Math.exp(logOdds) / (Math.exp(logOdds) + 1);
        double range = maxRate - minRate;
        return (probLiked * range) + minRate;

    }


}
