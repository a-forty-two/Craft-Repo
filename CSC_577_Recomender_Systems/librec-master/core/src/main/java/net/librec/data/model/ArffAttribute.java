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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiaxit
 */

public class ArffAttribute {
    private static final Set<String> VALID_TYPES = new HashSet<>(Arrays.asList(
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
            new String[]{"NUMERIC", "REAL", "INTEGER", "STRING", "NOMINAL"}
=======
            new String[] {"NUMERIC", "REAL", "INTEGER", "STRING", "NOMINAL"}
>>>>>>> master
=======
            new String[] {"NUMERIC", "REAL", "INTEGER", "STRING", "NOMINAL"}
>>>>>>> last minute commit.
    ));

    private String name;
    private String type;
    private int idx;

    private Set<String> columnSet;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    public ArffAttribute(String name, String type, int idx) {
=======
    public ArffAttribute(String name, String type, int idx){
>>>>>>> master
=======
    public ArffAttribute(String name, String type, int idx){
>>>>>>> last minute commit.
        // check if type is valid
        if (!VALID_TYPES.contains(type)) {
            throw new IllegalArgumentException("Invalid Type: " + type);
        }

        this.name = name;
        this.type = type;
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return idx;
    }

    public Set<String> getColumnSet() {
        return columnSet;
    }

    public void setColumnSet(Set<String> columnSet) {
        this.columnSet = columnSet;
    }

}
