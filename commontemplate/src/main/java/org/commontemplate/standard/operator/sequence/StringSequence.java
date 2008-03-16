package org.commontemplate.standard.operator.sequence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 字符串序列集合类
 *
 * @author liangfei0201@163.com
 *
 */
public class StringSequence implements Serializable { // FIXME 应该实现List接口

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
		this.sequence = java.util.Collections.unmodifiableList(sequence);
		this.cycle = cycle;
		this.ignoreCase = ignoreCase;
	}

	public List getSequence() {
		return sequence;
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
		if (beginIndex <= endIndex)
			return sequence.subList(beginIndex, endIndex + 1);
		if (cycle)
			return cycleList(beginIndex, endIndex);
		return reverseList(beginIndex, endIndex);
	}

	private List cycleList(int beginIndex, int endIndex) {
		List afterSub = sequence.subList(beginIndex, sequence.size());
		List beforeSub = sequence.subList(0, endIndex + 1);
		List sub = new ArrayList(afterSub.size() + beforeSub.size());
		sub.addAll(afterSub);
		sub.addAll(beforeSub);
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
				if (item.equalsIgnoreCase((String)sequence.get(i))) {
					return i;
				}
			}
			return -1;
		}
		return sequence.indexOf(item);
	}

}
