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
import net.librec.conf.Configurable;
=======
>>>>>>> master
=======
>>>>>>> last minute commit.
import net.librec.data.DataModel;
import net.librec.math.structure.SymmMatrix;

/**
 * Recommender Similarity
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 *
 * @author WangYuFeng
 */
public interface RecommenderSimilarity extends Configurable {

    public void buildSimilarityMatrix(DataModel dataModel, boolean isUser);

    public SymmMatrix getSimilarityMatrix();

    public void buildSocialSimilarityMatrix(DataModel dataModel);

    public SymmMatrix getSocialSimilarityMatrix();
=======
=======
>>>>>>> last minute commit.
 * 
 * @author WangYuFeng
 */
public interface RecommenderSimilarity {

	public void buildSimilarityMatrix(DataModel dataModel, boolean isUser);

	public SymmMatrix getSimilarityMatrix();

	
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}
