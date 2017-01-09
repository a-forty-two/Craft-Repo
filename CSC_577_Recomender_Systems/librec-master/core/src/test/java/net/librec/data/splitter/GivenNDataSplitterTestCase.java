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
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import net.librec.BaseTestCase;
import net.librec.common.LibrecException;
import net.librec.data.convertor.TextDataConvertor;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

/**
 * GivenNDataSplitter TestCase
 * {@link net.librec.data.splitter.GivenNDataSplitter}
 *
 * @author Liuxz and Sunyt
 */
public class GivenNDataSplitterTestCase extends BaseTestCase{

	private TextDataConvertor convertor;
	private TextDataConvertor convertorWithDate;

	@Before
	public void setUp() throws Exception {
		super.setUp();
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("dfs.data.dir", "../data");

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
		conf.set("data.splitter.given.n", "2");

		conf.set("data.model.splitter", "getgivennbyuser");
		GivenNDataSplitter givenNDataSplitter = new GivenNDataSplitter(dataConvertor, conf);
		givenNDataSplitter.splitData();

		conf.set("data.model.splitter","getgivennbyitem");
		givenNDataSplitter = new GivenNDataSplitter(dataConvertor, conf);
		givenNDataSplitter.splitData();
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
	}

	/**
	 * Test the methods splitData and getGivenNByUser
	 * givennbyuser: Each user splits out N ratings for test set,the rest for training set.
	 *
	 * @throws Exception
     */
	@Test
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
	public void testGivenNByUser() throws Exception {
		conf.set("data.splitter.givenn", "getgivennbyuser");
		conf.set("data.splitter.givenn.n", "1");
=======
	public void testGivenNByUser() throws Exception{
		conf.set("givenn.data.splitter", "getgivennbyuser");
		conf.set("data.splitter.given.n", "1");
>>>>>>> master
=======
	public void testGivenNByUser() throws Exception{
		conf.set("givenn.data.splitter", "getgivennbyuser");
		conf.set("data.splitter.given.n", "1");
>>>>>>> last minute commit.
		convertor.processData();

		GivenNDataSplitter splitter = new GivenNDataSplitter(convertor, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 4);
		assertEquals(splitter.getTestData().size(), 9);
	}

	/**
	 * Test the methods splitData and getGivenNByItem
	 * givennbyitem: Each user splits out N ratings for test set,the rest for training set.
	 *
	 * @throws Exception
	 */
	@Test
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
	public void testGivenNByItem() throws Exception {
		conf.set("data.splitter.givenn", "getgivennbyitem");
		conf.set("data.splitter.givenn.n", "1");
=======
	public void testGivenNByItem() throws Exception{
		conf.set("givenn.data.splitter", "getgivennbyitem");
		conf.set("data.splitter.given.n", "1");
>>>>>>> master
=======
	public void testGivenNByItem() throws Exception{
		conf.set("givenn.data.splitter", "getgivennbyitem");
		conf.set("data.splitter.given.n", "1");
>>>>>>> last minute commit.
		convertor.processData();

		GivenNDataSplitter splitter = new GivenNDataSplitter(convertor, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 4);
		assertEquals(splitter.getTestData().size(), 9);
	}

	/**
	 * Test the methods splitData and getGivenNByUserDate
	 * givennbyuserdate: Each user splits out N ratings for test set,the rest for training set.
	 *
	 * @throws Exception
	 */
	@Test
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
	public void testGivenNByUserDate() throws Exception {
		conf.set("data.splitter.givenn", "getgivennbyuserdate");
		conf.set("data.splitter.givenn.n", "1");
=======
	public void testGivenNByUserDate() throws Exception{
		conf.set("givenn.data.splitter", "getgivennbyuserdate");
		conf.set("data.splitter.given.n", "1");
>>>>>>> master
=======
	public void testGivenNByUserDate() throws Exception{
		conf.set("givenn.data.splitter", "getgivennbyuserdate");
		conf.set("data.splitter.given.n", "1");
>>>>>>> last minute commit.
		convertorWithDate.processData();

		GivenNDataSplitter splitter = new GivenNDataSplitter(convertorWithDate, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 4);
		assertEquals(splitter.getTestData().size(), 9);
	}

	/**
	 * Test the methods splitData and getGivenNByItemDate
	 * givennbyitemdate: Each item splits out N ratings for test set,the rest for training set.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGivenNByItemDate() throws Exception{
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
		conf.set("data.splitter.givenn", "getgivennbyitemdate");
		conf.set("data.splitter.givenn.n", "1");
=======
		conf.set("givenn.data.splitter", "getgivennbyitemdate");
		conf.set("data.splitter.given.n", "1");
>>>>>>> master
=======
		conf.set("givenn.data.splitter", "getgivennbyitemdate");
		conf.set("data.splitter.given.n", "1");
>>>>>>> last minute commit.
		convertorWithDate.processData();

		GivenNDataSplitter splitter = new GivenNDataSplitter(convertorWithDate, conf);
		splitter.splitData();

		assertEquals(splitter.getTrainData().size(), 4);
		assertEquals(splitter.getTestData().size(), 9);
	}
}
