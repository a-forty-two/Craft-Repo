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

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.librec.recommender.Recommender;
import org.apache.commons.lang.StringUtils;

=======
>>>>>>> master
=======
>>>>>>> last minute commit.
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
/**
 * Driver Class Util
 *
 * @author WangYuFeng
 */
public class DriverClassUtil {
    /**
     * driver Class BiMap matches configuration of driver.classes.props
     */
    private static BiMap<String, String> driverClassBiMap;
    /**
     * inverse configuration of driver.classes.props
     */
    private static BiMap<String, String> driverClassInverseBiMap;

    static {
        driverClassBiMap = HashBiMap.create();
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = DriverClassUtil.class.getClassLoader().getResourceAsStream("driver.classes.props");
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Iterator<Entry<Object, Object>> propIte = prop.entrySet().iterator();
        while (propIte.hasNext()) {
            Entry<Object, Object> entry = propIte.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            driverClassBiMap.put(key, value);
        }
        driverClassInverseBiMap = driverClassBiMap.inverse();
    }

    /**
     * get Class by driver name
     *
     * @param driver
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getClass(String driver) throws ClassNotFoundException {
        if (StringUtils.isBlank(driver)) {
            return null;
        } else if (StringUtils.contains(driver, ".")) {
            return Class.forName(driver);
        } else {
            String fullName = driverClassBiMap.get(driver);
            return Class.forName(fullName);
        }
    }

    /**
     * get Driver Name by clazz
     *
     * @param clazz
     * @return
     * @throws ClassNotFoundException
     */
    public static String getDriverName(String clazz) throws ClassNotFoundException {
        if (StringUtils.isBlank(clazz)) {
            return null;
        } else {
            return driverClassInverseBiMap.get(clazz);
        }
    }

    /**
     * get Driver Name by clazz
     *
     * @param clazz
     * @return
     * @throws ClassNotFoundException
     */
    public static String getDriverName(Class<? extends Recommender> clazz) throws ClassNotFoundException {
        if (clazz == null) {
            return null;
        } else {
            String driverName = driverClassInverseBiMap.get(clazz.getName());
            if (StringUtils.isNotBlank(driverName)) {
                return driverName;
            } else {
                return clazz.getSimpleName().toLowerCase().replace("recommender", "");
            }
        }
    }
=======
=======
>>>>>>> last minute commit.
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Driver Class Util
 * 
 * @author WangYuFeng
 */
public class DriverClassUtil {
	/**
	 * driver Class BiMap matches configuration of driver.classes.props
	 */
	private static BiMap<String, String> driverClassBiMap;
	/**
	 * inverse configuration of driver.classes.props
	 */
	private static BiMap<String, String> driverClassInverseBiMap;

	static {
		driverClassBiMap = HashBiMap.create();
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = DriverClassUtil.class.getClassLoader().getResourceAsStream("driver.classes.props");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Iterator<Entry<Object, Object>> propIte = prop.entrySet().iterator();
		while (propIte.hasNext()) {
			Entry<Object, Object> entry = propIte.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			driverClassBiMap.put(key, value);
		}
		driverClassInverseBiMap = driverClassBiMap.inverse();
	}

	/**
	 * get Class by driver name
	 * 
	 * @param driver
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getClass(String driver) throws ClassNotFoundException {
		if (StringUtils.isBlank(driver)) {
			return null;
		} else if (StringUtils.contains(driver, ".")) {
			return Class.forName(driver);
		} else {
			String fullName = driverClassBiMap.get(driver);
			return Class.forName(fullName);
		}
	}

	/**
	 * get Driver Name by clazz
	 * 
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static String getDriverName(String clazz) throws ClassNotFoundException {
		if (StringUtils.isBlank(clazz)) {
			return null;
		} else {
			return driverClassInverseBiMap.get(clazz);
		}
	}
	
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}
