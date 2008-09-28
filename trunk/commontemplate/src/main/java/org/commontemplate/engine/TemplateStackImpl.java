package org.commontemplate.engine;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.core.EventPublisher;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateStack;
import org.commontemplate.core.event.TemplatePopedEvent;
import org.commontemplate.core.event.TemplatePushedEvent;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

/**
 * 模板栈实现
 *
 * @author liangfei0201@163.com
 *
 */
final class TemplateStackImpl implements TemplateStack, Serializable {

	private static final long serialVersionUID = 1L;

	private final EventPublisher eventPublisher;

	private final TemplateNameFilter templateNameFilter;

	TemplateStackImpl(EventPublisher eventPublisher, TemplateNameFilter templateNameFilter) {
		this.eventPublisher = eventPublisher;
		this.templateNameFilter = templateNameFilter;
	}

	private final Stack stack = new LinkedStack();

	public Template getCurrentTemplate() {
		if (stack.isEmpty())
			return null;
		return (Template)stack.peek();
	}

	public void pushTemplate(Template template) {
		Template old = getCurrentTemplate();
		stack.push(template);
		eventPublisher.publishEvent(new TemplatePushedEvent(this, old, template));
	}

	public void popTemplate() {
		if (! stack.isEmpty()) {
			Template old = (Template)stack.pop();
			eventPublisher.publishEvent(new TemplatePopedEvent(this, old, getCurrentTemplate()));
		}
	}

	public int getTemplateStackSize() {
		return stack.size();
	}

	public List getTemplateStackValues() {
		return stack.list();
	}

	public void clearTemplates() {
		stack.clear();
	}

	public String relateTemplateName(String name) {
		if (templateNameFilter != null) {
			Template t = getCurrentTemplate();
			return templateNameFilter.filter(name, t == null ? null : t.getName());
		}
		return name;
	}

	public Template findTemplate(String name) {
		Assert.assertNotEmpty(name, "不能查找空的模板名称!");
		Template result = null;
		// 因LinkedStack使用LinkedList, 从头开始迭代快于倒序get()
		for (Iterator iterator = stack.iterator(); iterator.hasNext();) {
			Template template = (Template)iterator.next();
			if (template != null
					&& name.equals(template.getName())) {
				result = template;
				break;
			}
		}
		return result;
	}

}
