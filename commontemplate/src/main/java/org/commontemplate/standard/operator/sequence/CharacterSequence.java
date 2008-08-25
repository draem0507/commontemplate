package org.commontemplate.standard.operator.sequence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 连续的Unicode字符序列集合实现
 *
 * @author liangfei0201@163.com
 *
 */
public class CharacterSequence implements Sequence, Serializable {

	private static final long serialVersionUID = 1L;

	private char begin;

	private char end;

	private char min;

	private char max;

	private boolean asc;

	private int size;

	public CharacterSequence(char begin, char end) {
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

	public char getBegin() {
		return begin;
	}

	public char getEnd() {
		return end;
	}

	public int size() {
		return size;
	}

	public boolean isAsc() {
		return asc;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean contains(Object o) {
		char i = ((Character)o).charValue();
		return i >= min && i <= max;
	}

	public Iterator iterator() {
		return new CharacterSequenceIterator(begin, end);
	}

	public Object[] toArray() {
		Character[] arr = new Character[size];
		for (int i = 0, n = arr.length; i < n; i ++) {
			arr[i] = new Character((char)(begin + (asc?i:-i)));
		}
		return arr;
	}

	public Object[] toArray(Object[] arr) {
		for (int i = 0, n = arr.length; i < n && i < size; i ++) {
			if(asc) {
				arr[i] = new Character((char)(begin + i));
			} else {
				arr[i] = new Character((char)(begin - i));
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
		char value = (char)(begin + (asc ? index : - index));

		if ((asc && value > end) || (! asc && value < end))
			throw new IndexOutOfBoundsException("index = " + index);

		return new Character(value);
	}

	public int indexOf(Object o) {
		char i = ((Character)o).charValue();
		if (i < min || i > max) {
			return -1;
		}
		return asc?i - begin:begin - i;
	}

	public int lastIndexOf(Object o) {
		return indexOf(o);
	}

	public ListIterator listIterator() {
		return new CharacterSequenceIterator(begin, end);
	}

	public ListIterator listIterator(int index) {
		char beginIndex = (char)(begin + (asc ? index : - index));

		if ((asc && beginIndex > end) || (! asc && beginIndex < end))
			throw new IndexOutOfBoundsException("index = " + index);

		return new CharacterSequenceIterator(beginIndex, end);
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
        	return new CharacterSequence((char)(begin + fromIndex), (char)(begin + fromIndex + toIndex));
        }else {
        	return new CharacterSequence((char)(begin - fromIndex), (char)(begin - fromIndex - toIndex));
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
			for (char i = min; i <= max; i ++) {
				if (i != min)
					sb.append(",");
				sb.append((char)(asc ? i : max + min - i));
			}
			sb.append("]");
			buffer = sb.toString();
		}
		return buffer;
	}

	private static final class CharacterSequenceIterator implements ListIterator {

		private char begin;

		private char end;

		private char cur;

		private int inc;

		public CharacterSequenceIterator(char begin, char end) {
			this.begin = begin;
			this.end = end;
			cur = begin;
			inc = (begin < end ? 1 : -1);
		}

		public boolean hasNext() {
			return (inc>0?cur <= end:cur>=end);
		}

		public Object next() {
			if (isOverFlowEnd())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			Character next;
			if(isOverFlowOfBegin()) {
				cur = (char)(cur + inc);
				next = new Character(cur);
			} else {
				next = new Character(cur);
			}
			cur = (char)(cur + inc);

			return next;
		}

		public int nextIndex() {
			if (isOverFlowEnd())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			int index;
			if(isOverFlowOfBegin()) {
				cur = (char)(cur + inc);
				index = (inc > 0?cur - begin: begin - cur);
			} else {
				index = (inc > 0?cur - begin: begin - cur);
			}
			cur = (char)(cur + inc);

			return index;
		}

		public boolean hasPrevious() {
			return (inc>0?cur >= begin:cur<=begin);
		}

		public Object previous() {
			if (isOverFlowOfBegin())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			Character prev;
			if(isOverFlowEnd()) {
				cur = (char)(cur - inc);
				prev = new Character(cur);
			} else {
				prev = new Character(cur);
			}
			cur = (char)(cur - inc);

			return prev;
		}

		public int previousIndex() {
			if (isOverFlowOfBegin())
				throw new java.util.NoSuchElementException("IndexOutOfBounds");

			int index;
			if(isOverFlowEnd()) {
				cur = (char)(cur - inc);
				index = (inc > 0?cur - begin: begin - cur);
			} else {
				index = (inc > 0?cur - begin: begin - cur);
			}
			cur = (char)(cur - inc);

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

}
