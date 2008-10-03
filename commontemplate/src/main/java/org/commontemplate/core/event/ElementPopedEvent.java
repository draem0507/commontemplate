package org.commontemplate.core.event;

import org.commontemplate.core.Element;

/**
 * 模板栈弹栈事件
 *
 * @see org.commontemplate.core.ElementStack#popElement()
 * @author liangfei0201@163.com
 *
 */
public class ElementPopedEvent extends ElementStackEvent {

	private static final long serialVersionUID = 1L;

	public ElementPopedEvent(Object source, Element previous, Element current) {
		super(source, previous, current);
	}

}