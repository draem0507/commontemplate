package org.commontemplate.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Collection基类
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class CollectionSupport implements Collection {

	public boolean add(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	public Object[] toArray(Object[] a) {
		throw new UnsupportedOperationException();
	}

	public abstract Iterator iterator();

	public abstract int size();

}
