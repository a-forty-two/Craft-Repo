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
package net.librec.eval;

import net.librec.recommender.RecommenderContext;
import net.librec.recommender.item.RecommendedList;

/**
 * <p>
 * Implementations of this interface evaluate the quality of a
 * {@link net.librec.recommender.Recommender}'s recommendations.
 * </p>
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> master
=======
 * 
>>>>>>> last minute commit.
 * @author WangYuFeng
 */
public interface RecommenderEvaluator {

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    /**
     * evaluate
     *
     * @param context
     * @param recommendedList
     * @return
     */
    public double evaluate(RecommenderContext context, RecommendedList recommendedList);

    /**
     * set TopN
     *
     * @param topN
     */
    public void setTopN(int topN);
=======
=======
>>>>>>> last minute commit.
	/**
	 * evaluate
	 * 
	 * @param context
	 * @param recommendedList
	 * @return
	 */
	public double evaluate(RecommenderContext context, RecommendedList recommendedList);

	/**
	 * set TopN
	 * 
	 * @param topN
	 */
	public void setTopN(int topN);
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}
