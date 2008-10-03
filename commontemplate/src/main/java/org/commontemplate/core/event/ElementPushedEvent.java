package org.commontemplate.core.event;

import org.commontemplate.core.Element;

/**
 * 模板栈压栈事件
 *
 * @see org.commontemplate.core.ElementStack#pushElement(Element)
 * @author liangfei0201@163.com
 *
 */
public class ElementPushedEvent extends ElementStackEvent {

	private static final long serialVersionUID = 1L;

	public ElementPushedEvent(Object source, Element previous, Element current) {
		super(source, previous, current);
	}

}