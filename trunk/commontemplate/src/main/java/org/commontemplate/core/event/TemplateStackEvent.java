package org.commontemplate.core.event;

import org.commontemplate.core.Event;
import org.commontemplate.core.Template;

/**
 * 模板栈事件, 当模板栈中的模板改变时发生
 * 
 * @see org.commontemplate.core.TemplateStack
 * @author liangfei0201@163.com
 * 
 */
public abstract class TemplateStackEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final Template previous;

	private final Template current;

	/**
	 * 构造事件
	 * 
	 * @param source 事件发起者
	 * @param previous 栈顶原先模板
	 * @param current 栈顶当前模板
	 */
	public TemplateStackEvent(Object source, Template previous, Template current) {
		super(source);
		this.previous = previous;
		this.current = current;
	}

	/**
	 * 获取栈顶原先模板
	 * 
	 * @return 栈顶原先模板
	 */
	public Template getPreviousTemplate() {
		return previous;
	}

	/**
	 * 获取栈顶当前模板
	 * 
	 * @return 栈顶当前模板
	 */
	public Template getCurrentTemplate() {
		return current;
	}

}
