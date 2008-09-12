package org.commontemplate.util;

import java.util.Iterator;

/**
 * 数据栈
 *
 * Hack:
 * JDK类库中的java.util.Stack
 * 在设计方面有缺陷，故重新实现。
 *
 * @author liangfei0201@163.com
 *
 */
public interface Stack {

	/**
	 * 将数据压入栈
	 *
	 * @param value 被压入的数据
	 */
	public abstract void push(Object value);

	/**
	 * 将栈顶数据弹出
	 *
	 * @return 栈顶数据
	 */
	public abstract Object pop();

	/**
	 * 窥取栈顶数据, 不弹出
	 *
	 * @return 栈顶数据
	 */
	public abstract Object peek();

	/**
	 * 篡改栈顶数据, 相当于弹出并重新压入数据
	 *
	 * @param value 数据
	 */
	public abstract void poke(Object value);

	/**
	 * 判断栈是否为空
	 *
	 * @return true表示为空, false表示不为空
	 */
	public abstract boolean isEmpty();

	/**
	 * 清空栈
	 */
	public abstract int size();

	/**
	 * 清空栈
	 */
	public abstract void clear();

	/**
	 * 迭代栈数据
	 *
	 * @return 栈数据迭代器
	 */
	public abstract Iterator iterator();

}