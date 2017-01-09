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
package net.librec.recommender.item;

import java.util.Map;
import java.util.Objects;

/**
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
=======
 *
>>>>>>> master
=======
 *
>>>>>>> last minute commit.
 * @author YuFeng Wang
 */

/**
 * Hashtable bucket collision list entry
 */
public class ItemEntry<K, V> implements Map.Entry<K, V> {
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    K key;
    V value;

    // Map.Entry Ops

    public ItemEntry(K key, V value) {
        super();
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        if (value == null)
            throw new NullPointerException();

        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ItemEntry))
            return false;
        ItemEntry<?, ?> e = (ItemEntry<?, ?>) o;
        return key.equals(e.getKey()) && value.equals(e.getValue());
    }

    public int hashCode() {
        return (Objects.hashCode(key) ^ Objects.hashCode(value));
    }

    public String toString() {
        return key.toString() + "=" + value.toString();
    }
=======
=======
>>>>>>> last minute commit.
	K key;
	V value;

	// Map.Entry Ops

	public ItemEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V value) {
		if (value == null)
			throw new NullPointerException();

		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	public boolean equals(Object o) {
		if (!(o instanceof ItemEntry))
			return false;
		ItemEntry<?, ?> e =  (ItemEntry<?, ?>) o;
		return key.equals(e.getKey()) && value.equals(e.getValue());
	}

	public int hashCode() {
		return (Objects.hashCode(key) ^ Objects.hashCode(value));
	}

	public String toString() {
		return key.toString() + "=" + value.toString();
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}