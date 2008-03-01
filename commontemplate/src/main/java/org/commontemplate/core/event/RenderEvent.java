package org.commontemplate.core.event;

import org.commontemplate.core.Event;

/**
 * 模板元素渲染事件抽象类
 * 
 * @see org.commontemplate.core.Element
 * @author liangfei0201@163.com
 * 
 */
public abstract class RenderEvent extends Event {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造事件
	 * 
	 * @param source 发起事件的模板/模板元素
	 */
	public RenderEvent(Object source) {
		super(source);
	}

}
