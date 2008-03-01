package org.commontemplate.standard.operator.sequence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 连续的数字序列
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
			arr[i] = new Integer(begin + i);
		}
		return arr;
	}

	public Object[] toArray(Object a[]) {
		return a;
	}

	public boolean containsAll(Collection c) {
		return false;
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
		return i - begin;
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
        
		return new IntegerSequence(begin + fromIndex, begin + fromIndex + toIndex);
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
		
		private boolean first;
		
		public IntegerSequenceIterator(int begin, int end) {
			this.begin = begin;
			this.end = end;
			first = true;
			cur = begin;
			inc = begin < end ? 1 : -1; 
		}

		public boolean hasNext() {
			return first || cur != end;
		}

		public Object next() {
			if (first) {
				first = false;
				return new Integer(cur);
			}
			
			if (cur == end) 
				throw new java.util.NoSuchElementException("IndexOutOfBounds");
			
			cur = cur + inc;
			return new Integer(cur);
		}

		public int nextIndex() {
			if (first) {
				first = false;
				return 0; 
			}
			
			if (cur == end) 
				throw new java.util.NoSuchElementException("IndexOutOfBounds");
			
			cur = cur + inc;
			return cur - begin;
		}

		public boolean hasPrevious() {
			return first || cur != begin;
		}

		public Object previous() {
			if (first) {
				first = false;
				return new Integer(cur);
			}
			
			if (cur == begin) 
				throw new java.util.NoSuchElementException("IndexOutOfBounds");
			
			cur = cur - inc;
			return new Integer(cur);
		}

		public int previousIndex() {
			if (first) {
				first = false;
				return 0;
			}
			
			if (cur == begin) 
				throw new java.util.NoSuchElementException("IndexOutOfBounds");
			
			cur = cur - inc;
			return cur - begin;
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
		
	}

}
