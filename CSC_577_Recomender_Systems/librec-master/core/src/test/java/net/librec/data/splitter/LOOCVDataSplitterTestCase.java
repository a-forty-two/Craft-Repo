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
package net.librec.data.splitter;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import net.librec.BaseTestCase;
import net.librec.conf.Configured;
import net.librec.data.convertor.TextDataConvertor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
=======
=======
>>>>>>> last minute commit.
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import net.librec.BaseTestCase;
import net.librec.common.LibrecException;
import net.librec.data.convertor.TextDataConvertor;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

/**
 * LOOCVDataSplitter TestCase
 * {@link net.librec.data.splitter.LOOCVDataSplitter}
 *
 * @author Liuxz and Sunyt
 */
public class LOOCVDataSplitterTestCase extends BaseTestCase {

	private TextDataConvertor convertor;
	private TextDataConvertor convertorWithDate;

	@Before
	public void setUp() throws Exception {
		super.setUp();
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("inputDataPath", conf.get("dfs.data.dir") + "/test/sytTest4by4.txt");
		conf.set(Configured.CONF_DATA_COLUMN_FORMAT, "UIR");
		convertor = new TextDataConvertor(conf.get(Configured.CONF_DATA_COLUMN_FORMAT), conf.get("inputDataPath"));

		conf.set(Configured.CONF_DATA_COLUMN_FORMAT, "UIRT");
		conf.set("inputDataPath", conf.get("dfs.data.dir") + "/test/sytTestDate.txt");
		convertorWithDate = new TextDataConvertor(conf.get(Configured.CONF_DATA_COLUMN_FORMAT), conf.get("inputDataPath"));
=======
=======
>>>>>>> last minute commit.
		convertor = new TextDataConvertor("UIR","../data/Test/sytTest4by4.txt");
		convertorWithDate = new TextDataConvertor("UIRT","../data/Test/sytTestDate.txt");
	}

	@Test
	public void test() throws IOException, LibrecException {
		
		String inputDataPath = "/home/liuxz/librec/librecBase/data/MovieLens";
		TextDataConvertor dataConvertor = new TextDataConvertor("UIRT",inputDataPath);
		dataConvertor.processData();
		
		conf.set("data.model.splitter", "loobyuser");
		LOOCVDataSplitter loocvDataSplitter = new LOOCVDataSplitter(dataConvertor,conf);
		loocvDataSplitter.splitData();
		
		conf.set("data.model.splitter", "loobyitems");
		loocvDataSplitter = new LOOCVDataSplitter(dataConvertor,  conf);
		loocvDataSplitter.splitData();
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
	}

	/**
	 * Test the methods splitData and getLOOByUser
	 *
	 * @throws Exception
     */
	@Test
	public void testLOOByUser() throws Exception{
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("data.splitter.loocv", "loobyuser");
=======
		conf.set("loocv.data.splitter", "loobyuser");
>>>>>>> master
=======
		conf.set("loocv.data.splitter", "loobyuser");
>>>>>>> last minute commit.
		convertor.processData();

		LOOCVDataSplitter splitter = new LOOCVDataSplitter(convertor, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 9);
		assertEquals(splitter.getTestData().size(), 4);
	}

	/**
	 * Test the methods splitData and getLOOByItem
	 *
	 * @throws Exception
	 */
	@Test
	public void testLOOByItem() throws Exception{
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("data.splitter.loocv", "loobyitem");
=======
		conf.set("loocv.data.splitter", "loobyitem");
>>>>>>> master
=======
		conf.set("loocv.data.splitter", "loobyitem");
>>>>>>> last minute commit.
		convertor.processData();

		LOOCVDataSplitter splitter = new LOOCVDataSplitter(convertor, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 9);
		assertEquals(splitter.getTestData().size(), 4);
	}

	/**
	 * Test the methods splitData and getLOOByUserDate
	 *
	 * @throws Exception
	 */
	@Test
	public void testLOOByUserDate() throws Exception{
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("data.splitter.loocv", "loobyuserdate");
=======
		conf.set("loocv.data.splitter", "loobyuserdate");
>>>>>>> master
=======
		conf.set("loocv.data.splitter", "loobyuserdate");
>>>>>>> last minute commit.
		convertorWithDate.processData();

		LOOCVDataSplitter splitter = new LOOCVDataSplitter(convertorWithDate, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 9);
		assertEquals(splitter.getTestData().size(), 4);
	}

	/**
	 * Test the methods splitData and getLOOByItemDate
	 *
	 * @throws Exception
	 */
	@Test
	public void testLOOByItemDate() throws Exception{
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("data.splitter.loocv", "loobyitemdate");
=======
		conf.set("loocv.data.splitter", "loobyitemdate");
>>>>>>> master
=======
		conf.set("loocv.data.splitter", "loobyitemdate");
>>>>>>> last minute commit.
		convertorWithDate.processData();

		LOOCVDataSplitter splitter = new LOOCVDataSplitter(convertorWithDate, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 9);
		assertEquals(splitter.getTestData().size(), 4);
	}
}
