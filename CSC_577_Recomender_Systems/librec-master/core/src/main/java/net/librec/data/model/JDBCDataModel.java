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
package net.librec.data.model;

import com.google.common.collect.BiMap;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
=======

>>>>>>> master
=======

>>>>>>> last minute commit.
import net.librec.common.LibrecException;

/**
 * JDBC Data Model
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 *
 */
public class JDBCDataModel extends AbstractDataModel {


    @Override
    public void buildModel() throws LibrecException {
        // TODO Auto-generated method stub

    }

    @Override
    public BiMap<String, Integer> getUserMappingData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BiMap<String, Integer> getItemMappingData() {
        // TODO Auto-generated method stub
        return null;
    }
=======
=======
>>>>>>> last minute commit.
 * 
 */
public class JDBCDataModel extends AbstractDataModel {

	public void buildDataModel() {
	}

	public void loadDataModel() throws LibrecException {
		// TODO Auto-generated method stub
		
	}

	public void saveDataModel() throws LibrecException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BiMap<String, Integer> getUserMappingData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BiMap<String, Integer> getItemMappingData() {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}
