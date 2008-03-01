package org.commontemplate.core.event;

import org.commontemplate.core.Template;

/**
 * 模板栈弹栈事件
 * 
 * @see org.commontemplate.core.TemplateStack#popTemplate()
 * @author liangfei0201@163.com
 * 
 */
public class TemplatePopedEvent extends TemplateStackEvent {

	private static final long serialVersionUID = 1L;

	public TemplatePopedEvent(Object source, Template previous, Template current) {
		super(source, previous, current);
	}

}