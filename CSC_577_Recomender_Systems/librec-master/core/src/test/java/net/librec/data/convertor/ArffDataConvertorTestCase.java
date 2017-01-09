/**
 * Copyright (C) 2016 LibRec
 *
 * This file is part of LibRec.
 * LibRec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LibRec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LibRec. If not, see <http://www.gnu.org/licenses/>.
 */
package net.librec.data.convertor;

import net.librec.BaseTestCase;
import net.librec.common.LibrecException;
import net.librec.data.model.ArffInstance;
import net.librec.math.structure.SparseTensor;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import org.junit.Before;
import org.junit.Test;
=======
>>>>>>> master
=======
>>>>>>> last minute commit.

import java.io.IOException;
import java.util.ArrayList;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
=======
import org.junit.Before;
import org.junit.Test;

>>>>>>> master
=======
import org.junit.Before;
import org.junit.Test;

>>>>>>> last minute commit.
import static org.junit.Assert.assertEquals;

/**
 * Arff Data Convertor Test Case corresponds to ArffDataConvertor
 * {@link net.librec.data.convertor.ArffDataConvertor}
 *
 * @author MaChen & WangYuFeng
 */
public class ArffDataConvertorTestCase extends BaseTestCase {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test the function of reading a file.
	 *
	 * @throws ClassNotFoundException
	 * @throws LibrecException
	 * @throws IOException
     */
	@Test
	public void testReadFile() throws ClassNotFoundException, LibrecException, IOException {
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("inputDataPath", conf.get("dfs.data.dir") + "/arfftest/test.arff");
=======
		conf.set("inputDataPath", "../data/arfftest/test.arff");
>>>>>>> master
=======
		conf.set("inputDataPath", "../data/arfftest/test.arff");
>>>>>>> last minute commit.
		ArffDataConvertor arffLoder = new ArffDataConvertor(conf.get("inputDataPath"));
		try {
			arffLoder.readData();
		} catch (IOException e) {
			assert "read data failed".equals("");
		}
		// test case for tensor
		SparseTensor sparseTensor = arffLoder.getSparseTensor();
		ArrayList<ArffInstance> instances = arffLoder.getInstances();
		String s1 = arffLoder.getRelationName();
		System.out.println(s1);
		String s2 = arffLoder.getAttributes().get(0).getName();
		System.out.println(s2);
		System.out.println(sparseTensor.toString());

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		assertEquals(12, arffLoder.getInstances().size());
		assertEquals(5, arffLoder.getAttributes().size());
		assertEquals(12, sparseTensor.size());
=======
		assertEquals(6, arffLoder.getInstances().size());
		assertEquals(5, arffLoder.getAttributes().size());
		assertEquals(6, sparseTensor.size());
>>>>>>> master
=======
		assertEquals(6, arffLoder.getInstances().size());
		assertEquals(5, arffLoder.getAttributes().size());
		assertEquals(6, sparseTensor.size());
>>>>>>> last minute commit.
		assertEquals(4, sparseTensor.numDimensions());
	}

	/**
	 * Test the function of reading files from directory and its sub-directories.
	 *
	 * @throws ClassNotFoundException
	 * @throws LibrecException
	 * @throws IOException
     */
	@Test
	public void testReadDir() throws ClassNotFoundException, LibrecException, IOException {
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("inputDataPath", conf.get("dfs.data.dir") + "/arfftest");
=======
		conf.set("inputDataPath", "../data/arfftest");
>>>>>>> master
=======
		conf.set("inputDataPath", "../data/arfftest");
>>>>>>> last minute commit.
		ArffDataConvertor arffLoder = new ArffDataConvertor(conf.get("inputDataPath"));
		try {
			arffLoder.readData();
		} catch (IOException e) {
			assert "read data failed".equals("");
		}
		// test case for tensor
		SparseTensor sparseTensor = arffLoder.getSparseTensor();
		ArrayList<ArffInstance> instances = arffLoder.getInstances();
		String s1 = arffLoder.getRelationName();
		System.out.println(s1);
		String s2 = arffLoder.getAttributes().get(0).getName();
		System.out.println(s2);
		System.out.println(sparseTensor.toString());

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		assertEquals(18, arffLoder.getInstances().size());
		assertEquals(5, arffLoder.getAttributes().size());
		assertEquals(18, sparseTensor.size());
=======
		assertEquals(12, arffLoder.getInstances().size());
		assertEquals(5, arffLoder.getAttributes().size());
		assertEquals(12, sparseTensor.size());
>>>>>>> master
=======
		assertEquals(12, arffLoder.getInstances().size());
		assertEquals(5, arffLoder.getAttributes().size());
		assertEquals(12, sparseTensor.size());
>>>>>>> last minute commit.
		assertEquals(4, sparseTensor.numDimensions());
	}
}