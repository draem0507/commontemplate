package org.commontemplate.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 同步栈
 *
 * @author liangfei0201@163.com
 *
 */
public class SynchronizationStack implements Stack, Serializable {

	private static final long serialVersionUID = 1L;

	private final Stack targetStack;

	/**
	 * 默认使用LinkedStack
	 */
	public SynchronizationStack() {
		this.targetStack = new LinkedStack();
	}

	public SynchronizationStack(Stack targetStack) {
		this.targetStack = targetStack;
	}

	public synchronized void clear() {
		targetStack.clear();
	}

	public synchronized boolean isEmpty() {
		return targetStack.isEmpty();
	}

	public synchronized Object peek() {
		return targetStack.peek();
	}

	public synchronized void poke(Object value) {
		targetStack.poke(value);
	}

	public synchronized Object pop() {
		return targetStack.pop();
	}

	public synchronized void push(Object value) {
		targetStack.push(value);
	}

	public synchronized Iterator iterator() {
		return targetStack.iterator();
	}

	public int size() {
		return targetStack.size();
	}

	public List list() {
		return targetStack.list();
	}

}
