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
package net.librec.data.splitter;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
=======
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

>>>>>>> master
=======
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

>>>>>>> last minute commit.
import net.librec.conf.Configured;
import net.librec.data.DataConvertor;
import net.librec.data.DataSplitter;
import net.librec.math.structure.SparseMatrix;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract Data Splitter
 *
 * @author WangYuFeng
 */
public abstract class AbstractDataSplitter extends Configured implements DataSplitter {
    /**
     * LOG
     */
    protected final Log LOG = LogFactory.getLog(this.getClass());
    /**
     * dataConvertor
     */
    protected DataConvertor dataConvertor;
    /**
     * trainMatrix
     */
    protected SparseMatrix trainMatrix;
    /**
     * testMatrix
     */
    protected SparseMatrix testMatrix;
    /**
     * validationMatrix
     */
    protected SparseMatrix validationMatrix;

    /**
     * @param dataConvertor
     *            the dataConvertor to set
     */
    public void setDataConvertor(DataConvertor dataConvertor) {
        this.dataConvertor = dataConvertor;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.librec.data.DataModel#getTraningData()
     */
    public SparseMatrix getTrainData() {
        return trainMatrix;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.librec.data.DataModel#getTestData()
     */
    public SparseMatrix getTestData() {
        return testMatrix;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.librec.data.DataModel#getValidationData()
     */
    public SparseMatrix getValidData() {
        return validationMatrix;
    }
=======
=======
>>>>>>> last minute commit.

/**
 * Abstract Data Splitter
 * 
 * @author WangYuFeng
 */
public abstract class AbstractDataSplitter extends Configured implements DataSplitter {
	/**
	 * LOG
	 */
	protected final Log LOG = LogFactory.getLog(this.getClass());
	/**
	 * dataConvertor
	 */
	protected DataConvertor dataConvertor;
	/**
	 * trainMatrix
	 */
	protected SparseMatrix trainMatrix;
	/**
	 * testMatrix
	 */
	protected SparseMatrix testMatrix;
	/**
	 * validationMatrix
	 */
	protected SparseMatrix validationMatrix;

	/**
	 * @param dataConvertor
	 *            the dataConvertor to set
	 */
	public void setDataConvertor(DataConvertor dataConvertor) {
		this.dataConvertor = dataConvertor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.librec.data.DataModel#getTraningData()
	 */
	public SparseMatrix getTrainData() {
		return trainMatrix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.librec.data.DataModel#getTestData()
	 */
	public SparseMatrix getTestData() {
		return testMatrix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.librec.data.DataModel#getValidationData()
	 */
	public SparseMatrix getValidData() {
		return validationMatrix;
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}
