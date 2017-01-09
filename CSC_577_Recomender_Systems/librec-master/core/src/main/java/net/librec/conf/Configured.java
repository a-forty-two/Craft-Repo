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
package net.librec.conf;

/**
 * Base class for things that may be configured with a {@link Configuration}.
 */
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
public class Configured implements Configurable {

    public static final String CONF_DATA_COLUMN_FORMAT = "data.column.format";

    public static final String CONF_DFS_DATA_DIR = "dfs.data.dir";

    public static final String CONF_DATA_INPUT_PATH = "data.input.path";

    protected Configuration conf;

    /** Construct a Configured. */
    public Configured() {
        this(null);
    }

    /** Construct a Configured. */
    public Configured(Configuration conf) {
        setConf(conf);
    }

    // inherit javadoc
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    // inherit javadoc
    public Configuration getConf() {
        return conf;
    }
=======
=======
>>>>>>> last minute commit.
public class Configured implements Configurable{

	public static final String CONF_DATA_COLUMN_FORMAT = "data.column.format";

	public static final String CONF_DFS_DATA_DIR = "dfs.data.dir";

	public static final String CONF_DATA_INPUT_PATH = "data.input.path";


	protected Configuration conf;

	/** Construct a Configured. */
	public Configured() {
		this(null);
	}

	/** Construct a Configured. */
	public Configured(Configuration conf) {
		setConf(conf);
	}

	// inherit javadoc
	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	// inherit javadoc
	public Configuration getConf() {
		return conf;
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}
