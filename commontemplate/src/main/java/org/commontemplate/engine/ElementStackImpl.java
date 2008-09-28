package org.commontemplate.engine;

import java.util.Iterator;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Element;
import org.commontemplate.core.ElementStack;
import org.commontemplate.core.EventPublisher;
import org.commontemplate.core.LocalContextStack;
import org.commontemplate.core.event.ElementPopedEvent;
import org.commontemplate.core.event.ElementPushedEvent;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

class ElementStackImpl implements ElementStack {

	private final EventPublisher eventPublisher;

	private final LocalContextStack localContextStack;

	private final Stack stack = new LinkedStack();

	ElementStackImpl(EventPublisher eventPublisher, LocalContextStack localContextStack) {
		this.eventPublisher = eventPublisher;
		this.localContextStack = localContextStack;
	}

	public void pushElement(Element element) {
		if (element instanceof BlockDirective) {
			localContextStack.pushLocalContext(element.getName());
		}
		Element old = getCurrentElement();
		stack.push(element);
		eventPublisher.publishEvent(new ElementPushedEvent(this, old, element));
	}

	public void popElement() {
		if (! stack.isEmpty()) {
			Element element = (Element)stack.pop();
			eventPublisher.publishEvent(new ElementPopedEvent(this, element, getCurrentElement()));
			if (element instanceof BlockDirective) {
				localContextStack.popLocalContext();
			}
		}
	}

	public Element getCurrentElement() {
		if (stack.isEmpty())
			return null;
		return (Element)stack.peek();
	}

	public Element findElement(String name) {
		Assert.assertNotEmpty(name);
		Element result = null;
		// 因LinkedStack使用LinkedList, 从头开始迭代快于倒序get()
		if (! stack.isEmpty()) {
			for (Iterator iterator = stack.iterator(); iterator.hasNext();) {
				Element element = (Element)iterator.next();
				if (element != null
						&& name.equals(element.getName()))
					result = element;
			}
		}
		return result;
	}

	public List getElementStackValues() {
		return stack.list();
	}

	public void clearElements() {
		stack.clear();
	}

}
