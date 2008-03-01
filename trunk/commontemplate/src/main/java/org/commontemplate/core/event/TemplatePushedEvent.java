package org.commontemplate.core.event;

import org.commontemplate.core.Template;

/**
 * 模板栈压栈事件
 * 
 * @see org.commontemplate.core.TemplateStack#pushTemplate(Template)
 * @author liangfei0201@163.com
 * 
 */
public class TemplatePushedEvent extends TemplateStackEvent {

	private static final long serialVersionUID = 1L;

	public TemplatePushedEvent(Object source, Template previous, Template current) {
		super(source, previous, current);
	}

}