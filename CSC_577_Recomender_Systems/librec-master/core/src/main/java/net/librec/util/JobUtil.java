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
package net.librec.util;

import java.text.NumberFormat;
import java.util.Date;

/**
 * JobUtil
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
public class JobUtil {

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    private static final String JOB = "job";

    private static int identifier = 0;

    public static String generateNewJobId() {
        return (new StringBuilder(JOB).append("_").append(generateNewIdentifier()).append("_").append(getIdentifierId())).toString();
    }

    private static int getIdentifierId() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumIntegerDigits(4);
        numberFormat.setGroupingUsed(false);
        return Integer.parseInt(numberFormat.format(identifier++));
    }

    private static String generateNewIdentifier() {
        return DateUtil.getDateFormat("yyyyMMddhhmmssSSS").format(new Date());
    }
=======
=======
>>>>>>> last minute commit.
	private static final String JOB = "job";

	private static int identifier = 0;

	public static String generateNewJobId() {
		return (new StringBuilder(JOB).append("_").append(generateNewIdentifier()).append("_").append(getIdentifierId())).toString();
	}

	private static int getIdentifierId() {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumIntegerDigits(4);
		numberFormat.setGroupingUsed(false);
		return Integer.parseInt(numberFormat.format(identifier++));
	}

	private static String generateNewIdentifier() {
		return DateUtil.getDateFormat("yyyyMMddhhmmssSSS").format(new Date());
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}