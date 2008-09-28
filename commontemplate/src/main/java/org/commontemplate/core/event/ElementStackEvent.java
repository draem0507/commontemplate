package org.commontemplate.core.event;

import org.commontemplate.core.Event;
import org.commontemplate.core.Element;

public abstract class ElementStackEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Element previous;

	private final Element current;

	/**
	 * 构造事件
	 *
	 * @param source 事件发起者
	 * @param previous 栈顶原先模板
	 * @param current 栈顶当前模板
	 */
	public ElementStackEvent(Object source, Element previous, Element current) {
		super(source);
		this.previous = previous;
		this.current = current;
	}

	/**
	 * 获取栈顶原先模板
	 *
	 * @return 栈顶原先模板
	 */
	public Element getPreviousElement() {
		return previous;
	}

	/**
	 * 获取栈顶当前模板
	 *
	 * @return 栈顶当前模板
	 */
	public Element getCurrentElement() {
		return current;
	}

}
