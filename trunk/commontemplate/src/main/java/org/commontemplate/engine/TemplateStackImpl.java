package org.commontemplate.engine;

import java.util.Iterator;

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
final class TemplateStackImpl implements TemplateStack {
	
	private final EventPublisher eventPublisher;
	
	private final TemplateNameFilter templateNameFilter;
	
	TemplateStackImpl(EventPublisher eventPublisher, TemplateNameFilter templateNameFilter) {
		this.eventPublisher = eventPublisher;
		this.templateNameFilter = templateNameFilter;
	}
	
	private final Stack templateNameStack = new LinkedStack();
	
	public Template getCurrentTemplate() {
		if (templateNameStack.isEmpty())
			return null;
		return (Template)templateNameStack.peek();
	}

	public void pushTemplate(Template template) {
		Template old = getCurrentTemplate();
		templateNameStack.push(template);
		eventPublisher.publishEvent(new TemplatePushedEvent(this, old, template));
	}

	public void popTemplate() {
		Template old = (Template)templateNameStack.pop();
		eventPublisher.publishEvent(new TemplatePopedEvent(this, old, getCurrentTemplate()));
	}

	public Iterator getTemplateStackValues() {
		return templateNameStack.iterator();
	}

	public void clearTemplates() {
		templateNameStack.clear();
	}

	public boolean containsTemplate(String name) {
		for (Iterator iterator =  templateNameStack.iterator(); iterator.hasNext();) {
			Template template = (Template)iterator.next();
			if (template != null 
					&& name.equals(template.getName())) 
				return true;
		}
		return false;
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
		for (Iterator iterator = templateNameStack.iterator(); iterator.hasNext();) {
			Template template = (Template)iterator.next();
			if (template != null 
					&& name.equals(template.getName())) 
				result = template;
		}
		return result;
	}

}
