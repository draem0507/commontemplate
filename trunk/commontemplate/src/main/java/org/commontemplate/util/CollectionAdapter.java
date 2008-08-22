package org.commontemplate.util;

import java.util.Iterator;

public class CollectionAdapter extends CollectionSupport {

	private Iterator iterator;

	private int size;

	public CollectionAdapter(Iterator iterator, int size) {
		this.iterator = iterator;
		this.size = size;
	}

	public Iterator iterator() {
		return iterator;
	}

	public int size() {
		return size;
	}

}
