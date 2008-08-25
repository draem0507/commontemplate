package org.commontemplate.standard.converter;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayIterator implements Iterator {

	private final Object array;

	private final int length;

	private int index = 0;

	public ArrayIterator(Object array) {
		this.array = array;
		this.length = Array.getLength(array);
	}

	public boolean hasNext() {
		return index < length;
	}

	public Object next() {
		if (! hasNext())
			throw new java.util.NoSuchElementException();
		return Array.get(array, index ++);
	}

	public void remove() {
		throw new java.lang.UnsupportedOperationException();
	}

}
