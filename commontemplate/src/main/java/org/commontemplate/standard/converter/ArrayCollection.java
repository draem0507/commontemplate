package org.commontemplate.standard.converter;

import java.lang.reflect.Array;
import java.util.Iterator;

import org.commontemplate.util.CollectionSupport;

public class ArrayCollection extends CollectionSupport {

	private final Object array;

	private final int length;

	public ArrayCollection(Object array) {
		this.array = array;
		this.length = Array.getLength(array);
	}

	public int size() {
		return length;
	}

	public Iterator iterator() {
		return new ArrayIterator(array);
	}

}
