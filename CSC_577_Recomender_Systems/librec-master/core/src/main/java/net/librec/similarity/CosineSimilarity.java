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

import java.util.List;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
/**
 * Cosine similarity
 *
=======
/** 
 * Cosine similarity
 * 
>>>>>>> master
=======
/** 
 * Cosine similarity
 * 
>>>>>>> last minute commit.
 * @author zhanghaidong
 */
public class CosineSimilarity extends AbstractRecommenderSimilarity {

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    /**
     * Calculate the cosine similarity
     */
    protected double getSimilarity(List<? extends Number> thisList, List<? extends Number> thatList) {
        if (thisList == null || thatList == null || thisList.size() < 1 || thatList.size() < 1 || thisList.size() != thatList.size()) {
            return Double.NaN;
        }

        double innerProduct = 0.0, thisPower2 = 0.0, thatPower2 = 0.0;
        for (int i = 0; i < thisList.size(); i++) {
            innerProduct += thisList.get(i).doubleValue() * thatList.get(i).doubleValue();
            thisPower2 += thisList.get(i).doubleValue() * thisList.get(i).doubleValue();
            thatPower2 += thatList.get(i).doubleValue() * thatList.get(i).doubleValue();
        }
        return innerProduct / Math.sqrt(thisPower2 * thatPower2);
    }
=======
=======
>>>>>>> last minute commit.
	/**
	 * Calculate the cosine similarity
	 */
	protected double getSimilarity(List<? extends Number> thisList, List<? extends Number> thatList) {
		if (thisList == null || thatList == null || thisList.size() < 1 || thatList.size() < 1 || thisList.size() != thatList.size()) {
			return Double.NaN;
		}
		
		double innerProduct = 0.0, thisPower2 = 0.0, thatPower2 = 0.0;
		for (int i = 0; i < thisList.size(); i++){
			innerProduct += thisList.get(i).doubleValue() * thatList.get(i).doubleValue();
			thisPower2 += thisList.get(i).doubleValue() * thisList.get(i).doubleValue();
			thatPower2 += thatList.get(i).doubleValue() * thatList.get(i).doubleValue();
		}
		return innerProduct / Math.sqrt(thisPower2 * thatPower2);
	}


<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}