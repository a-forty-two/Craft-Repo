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
package net.librec.data.convertor.feature;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import com.google.common.collect.BiMap;
import net.librec.data.DataFeature;

import java.io.IOException;

/**
 * Document Data Feature
 * @author WangYuFeng
 */
public class DocumentDataFeature implements DataFeature {

    @Override
    public void processData() throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setUserMappingData(BiMap<String, Integer> userMappingData) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setItemMappingData(BiMap<String, Integer> itemMappingData) {
        // TODO Auto-generated method stub

    }
=======
=======
>>>>>>> last minute commit.
import net.librec.data.DataFeature;
import net.librec.math.structure.SparseMatrix;

/**
 * Document Data Feature
 * @author YuFeng Wang
 */
public class DocumentDataFeature implements DataFeature {

	/* (non-Javadoc)
	 * @see net.librec.data.DataFeature#getUserFeature()
	 */
	public SparseMatrix getUserFeature() {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.librec.data.DataFeature#getItemFeature()
	 */
	public SparseMatrix getItemFeature() {
		return null;
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}
