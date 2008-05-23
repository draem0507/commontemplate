package org.commontemplate.standard.operator.sequence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 连续的数字序列集合实现
 *
 * @author liangfei0201@163.com
 *
 */
public class IntegerSequence implements List, Serializable {

	private static final long serialVersionUID = 1L;

	private int begin;

	private int end;

	private int min;

	private int max;

	private boolean asc;

	private int size;

	public IntegerSequence(int begin, int end) {
		this.begin = begin;
		this.end = end;
		if (begin < end) {
			min = begin;
			max = end;
			asc = true;
		} else {
			min = end;
			max = begin;
			asc = false;
		}
		size = max - min + 1;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public boolean isAscending() {
		return asc;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean contains(Object o) {
		int i = ((Integer)o).intValue();
		return i >= min && i <= max;
	}

	public Iterator iterator() {
		return new IntegerSequenceIterator(begin, end);
	}

	public Object[] toArray() {
		Integer[] arr = new Integer[size];
		for (int i = 0, n = arr.length; i < n; i ++) {
			if(asc) {
				arr[i] = new Integer(begin + i);
			} else {
				arr[i] = new Integer(begin - i);
			}
		}
		return arr;
	}

	public Object[] toArray(Object arr[]) {
		for (int i = 0, n = arr.length; i < n && i < size; i ++) {
			if(asc) {
				arr[i] = new Integer(begin + i);
			} else {
				arr[i] = new Integer(begin - i);
			}
		}
		return arr;
	}

	public boolean containsAll(Collection c) {

		Iterator it = c.iterator();
		Object obj;
		while(it.hasNext()) {

			obj = it.next();
			if(!contains(obj)) {
				return false;
			}
		}

		return true;
	}

	public Object get(int index) {
		int value = begin + (asc ? index : - index);

		if ((asc && value > end) || (! asc && value < end))
			throw new IndexOutOfBoundsException("index = " + index);

		return new Integer(value);
	}

	public int indexOf(Object o) {
		int i = ((Integer)o).intValue();
		if (i < min || i > max) {
			return -1;
		}
		return (asc? i - begin:begin - i);
	}

	public int lastIndexOf(Object o) {
		return indexOf(o);
	}

	public ListIterator listIterator() {
		return new IntegerSequenceIterator(begin, end);
	}

	public ListIterator listIterator(int index) {
		int beginIndex = begin + (asc ? index : - index);

		if ((asc && beginIndex > end) || (! asc && beginIndex < end))
			throw new IndexOutOfBoundsException("index = " + index);

		return new IntegerSequenceIterator(beginIndex, end);
	}

	public List subList(int fromIndex, int toIndex) {
		if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size())
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                                               ") > toIndex(" + toIndex + ")");
        if(asc) {
        	return new IntegerSequence(begin + fromIndex, begin + fromIndex + toIndex);
        } else {
        	return new IntegerSequence(begin - fromIndex, begin - fromIndex - toIndex);
        }
	}

	public boolean add(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(int index, Collection c) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public Object set(int index, Object element) {
		throw new UnsupportedOperationException();
	}

	public void add(int index, Object element) {
		throw new UnsupportedOperationException();
	}

	public Object remove(int index) {
		throw new UnsupportedOperationException();
	}

	private String buffer;

	public String toString() {
		if (buffer == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = min; i <= max; i ++) {
				if (i != min)
					sb.append(",");
				sb.append(asc ? i : max + min - i);
			}
			sb.append("]");
			buffer = sb.toString();
		}
		return buffer;
	}

	private static final class IntegerSequenceIterator implements ListIterator {

		private int begin;

		private int end;

		private int cur;

		private int inc;

		public IntegerSequenceIterator(int begin, int end) {
			this.begin = begin;
			this.end = end;
			cur = begin;
			inc = begin < end ? 1 : -1;
		}

		public boolean hasNext() {
			return (inc>0?cur <= end:cur>=end);
		}

		public Object next() {

			if (isOverFlowEnd())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			Integer next;
			if(isOverFlowOfBegin()) {
				cur = cur + inc;
				next = new Integer(cur);
			} else {
				next = new Integer(cur);
			}
			cur = cur + inc;

			return next;
		}

		public int nextIndex() {

			if (isOverFlowEnd())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			int index;
			if(isOverFlowOfBegin()) {
				cur = cur + inc;
				index = (inc > 0?cur - begin: begin - cur);
			} else {
				index = (inc > 0?cur - begin: begin - cur);
			}
			cur = cur + inc;

			return index;
		}

		public boolean hasPrevious() {
			return (inc>0?cur >= begin:cur<=begin);
		}

		public Object previous() {

			if (isOverFlowOfBegin())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			Integer prev;
			if(isOverFlowEnd()) {
				cur = cur - inc;
				prev = new Integer(cur);
			} else {
				prev = new Integer(cur);
			}
			cur = cur - inc;

			return prev;
		}

		public int previousIndex() {

			if (isOverFlowOfBegin())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			int index;
			if(isOverFlowEnd()) {
				cur = cur - inc;
				index = (inc > 0?cur - begin: begin - cur);
			} else {
				index = (inc > 0?cur - begin: begin - cur);
			}
			cur = cur - inc;

			return index;
		}

		public void add(Object o) {
			throw new UnsupportedOperationException();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public void set(Object o) {
			throw new UnsupportedOperationException();
		}


		private boolean isOverFlowOfBegin() {
			return (inc > 0 && cur < begin) ||
					(inc < 0 && cur > begin);
		}

		private boolean isOverFlowEnd() {
			return (inc > 0 && cur > end) ||
					(inc < 0 && cur < end);
		}

	}

	public boolean isAsc() {
		return asc;
	}

}
