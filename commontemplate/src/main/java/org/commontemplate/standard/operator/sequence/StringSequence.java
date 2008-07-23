package org.commontemplate.standard.operator.sequence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 字符串序列集合类
 *
 * @author liangfei0201@163.com
 *
 */
public class StringSequence implements Sequence, Serializable {

	private static final long serialVersionUID = 1L;

	private final List sequence;

	private final boolean cycle;

	private final boolean ignoreCase;

	public StringSequence(String sequence, boolean cycle, boolean ignoreCase) {
		this(sequence.split("\\,"), cycle, ignoreCase);
	}

	public StringSequence(String[] sequence, boolean cycle, boolean ignoreCase) {
		this(Arrays.asList(sequence), cycle, ignoreCase);
	}

	public StringSequence(List sequence, boolean cycle, boolean ignoreCase) {

		// Arrays.asList 得到的List是Unmodify的，所以这个地方处理一下。
		List list;
		try {
			list = (List) sequence.getClass().newInstance();
		} catch (Exception e) {

			list = new ArrayList();
		}
		// 循环增加。因为如果构造函数传入一个 "" , sequence.split("\\,")
		// 方法仍然给分割成一个数组。
		for(int i = 0, m = sequence.size(); i < m; i++) {

			if(!"".equals(sequence.get(i))) {
				list.add(sequence.get(i));
			}
		}

		this.sequence = list;
		this.cycle = cycle;
		this.ignoreCase = ignoreCase;
	}

	public List getSequence() {
		return unmodifyList(sequence);
	}

	public boolean isCycle() {
		return cycle;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public boolean containSequence(String begin, String end) {
		return indexOf(begin) != -1 && indexOf(end) != -1;
	}

	public List getSequence(String begin, String end) {
		int beginIndex = indexOf(begin);
		int endIndex = indexOf(end);

		if(beginIndex == -1 || endIndex == -1) {
			return Arrays.asList(new String[0]);
		}

		if (beginIndex <= endIndex)
			return unmodifyList(sequence.subList(beginIndex, endIndex + 1));
		if (cycle)
			return unmodifyList(cycleList(beginIndex, endIndex));
		return unmodifyList(reverseList(beginIndex, endIndex));
	}

	private List cycleList(int beginIndex, int endIndex) {
		List afterSub = sequence.subList(beginIndex, sequence.size());
		List beforeSub = sequence.subList(0, endIndex + 1);
		List sub = new ArrayList(afterSub.size() + beforeSub.size());
		sub.addAll(afterSub);
		sub.addAll(beforeSub);
		sub = unmodifyList(sub);
		return sub;
	}

	private List reverseList(int beginIndex, int endIndex) {
		List sub = sequence.subList(endIndex, beginIndex + 1);
		Collections.reverse(sub);
		return sub;
	}

	private int indexOf(String item) {
		if (ignoreCase) {
			for (int i = 0, n = sequence.size(); i < n; i ++) {
				if (((String)sequence.get(i)).equalsIgnoreCase(item)) {
					return i;
				}
			}
			return -1;
		}
		return sequence.indexOf(item);
	}
	/**
	 * 把一个List设置为Unmodify状态。<br>
	 * 原则上，这个类中所有的public方法得到的list, 返回之前都应该调用这个方法来修改<br>
	 * 返回值。
	 * @param list
	 * List 对象。
	 * @return
	 * unmodifiable的List。
	 */
	private List unmodifyList(List list) {
		return java.util.Collections.unmodifiableList(list);
	}

	public void add(int index, Object element) {

		sequence.add(index, element);
	}

	public boolean add(Object o) {
		return sequence.add(o);
	}

	public boolean addAll(Collection c) {
		return sequence.addAll(c);
	}

	public boolean addAll(int index, Collection c) {
		return sequence.addAll(index, c);
	}

	public void clear() {
		sequence.clear();
	}

	public boolean contains(Object o) {
		return sequence.contains(o);
	}

	public boolean containsAll(Collection c) {
		return sequence.containsAll(c);
	}

	public boolean equals(Object o) {
		return sequence.equals(o);
	}

	public Object get(int index) {
		return sequence.get(index);
	}

	public int hashCode() {
		return sequence.hashCode();
	}

	public int indexOf(Object o) {
		return sequence.indexOf(o);
	}

	public boolean isEmpty() {
		return sequence.isEmpty();
	}

	public Iterator iterator() {
		return sequence.iterator();
	}

	public int lastIndexOf(Object o) {
		return sequence.lastIndexOf(o);
	}

	public ListIterator listIterator() {
		return sequence.listIterator();
	}

	public ListIterator listIterator(int index) {
		return sequence.listIterator(index);
	}

	public Object remove(int index) {
		return sequence.remove(index);
	}

	public boolean remove(Object o) {
		return sequence.remove(o);
	}

	public boolean removeAll(Collection c) {
		return sequence.removeAll(c);
	}

	public boolean retainAll(Collection c) {
		return sequence.retainAll(c);
	}

	public Object set(int index, Object element) {
		return sequence.set(index, element);
	}

	public int size() {
		return sequence.size();
	}

	public List subList(int fromIndex, int toIndex) {
		return unmodifyList(sequence.subList(fromIndex, toIndex));
	}

	public Object[] toArray() {
		return sequence.toArray();
	}

	public Object[] toArray(Object[] a) {
		return sequence.toArray(a);
	}

}
