package org.commontemplate.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;


/**
 * 链表栈.
 * (非线程安全, 请保证单线程使用, 或使用<code>SynchronizationStack</code>同步)
 * @author liangfei0201@163.com
 *
 */
public class LinkedStack implements Stack, Serializable {

	private static final long serialVersionUID = 1L;

	private final LinkedList stack = new LinkedList();

	public int size() {
		return stack.size();
	}

	public boolean isEmpty() {
		return stack.size() == 0;
	}

	public void push(Object value) {
		stack.addLast(value);
	}

	public Object pop() {
		Object value = peek();
		stack.removeLast();
		return value;
	}

	public Object peek() {
		Assert.assertFalse(isEmpty(), "LinkedStack.empty.stack");
		return stack.getLast();
	}

	public void poke(Object value) {
		Assert.assertFalse(isEmpty(), "LinkedStack.empty.stack");
		stack.removeLast();
		push(value);
	}

	public void clear() {
		stack.clear();
	}

	public Iterator iterator() {
		return Collections.unmodifiableList(stack).iterator();
	}

	public String toString() {
		return stack.toString();
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (o instanceof LinkedStack)
			return false;
		LinkedStack s = (LinkedStack)o;
		return stack.equals(s.stack);
	}

	public int hashCode() {
		return 37 * stack.hashCode();
	}

}
