package org.commontemplate.core.event;

import org.commontemplate.core.Event;
import org.commontemplate.core.LocalContext;

/**
 * 上下文栈事件, 当上下文栈中的上下文改变时发生
 * 
 * @see org.commontemplate.core.LocalContextStack
 * @author liangfei0201@163.com
 *
 */
public abstract class LocalContextStackEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final LocalContext current;
	
	private final LocalContext previous;
	
	/**
	 * 构造事件
	 * 
	 * @param source 事件发起者
	 * @param previous 栈顶原先上下文
	 * @param current 栈顶当前上下文
	 */
	public LocalContextStackEvent(Object source, LocalContext previous, LocalContext current) {
		super(source);
		this.current = current;
		this.previous = previous;
	}

	/**
	 * 获取当前上下文
	 * 
	 * @return 当前上下文
	 */
	public LocalContext getCurrentLocalContext() {
		return current;
	}
	
	/**
	 * 获取原先上下文
	 * 
	 * @return 原先上下文
	 */
	public LocalContext getPreviousLocalContext() {
		return previous;
	}

}
