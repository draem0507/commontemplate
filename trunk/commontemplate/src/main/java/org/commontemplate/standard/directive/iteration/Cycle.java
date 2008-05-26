package org.commontemplate.standard.directive.iteration;

import java.util.List;

import org.commontemplate.util.Assert;

/**
 * 循环取值集合
 *
 * @author liangfei0201@163.com
 *
 */
public class Cycle {

	private final List values;

	private int index;

	public Cycle(List values) {
		Assert.assertNotEmpty(values, "Cycle.items.required");
		this.values = values;
		this.index = -1;
	}

	public Object getNext() {
		index += 1;
		if (index >= values.size())
			index = 0;
		return getValue();
	}

	public int getIndex() {
		return index;
	}

	public Object getValue() {
		if (index == -1)
			return null;
		return values.get(index);
	}

	public List values() {
		return values;
	}

	public String toString() {
		return values.toString();
	}

}
