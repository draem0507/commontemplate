package org.commontemplate.engine;

import java.io.Serializable;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.Context;
import org.commontemplate.core.EventPublisher;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.LocalContextStack;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.event.LocalContextPopedEvent;
import org.commontemplate.core.event.LocalContextPushedEvent;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

/**
 * 局部上下文栈实现
 *
 * @author liangfei0201@163.com
 *
 */
final class LocalContextStackImpl implements LocalContextStack, Serializable {

	private static final long serialVersionUID = 1L;

	private transient final Writer out;

	private final EventPublisher eventPublisher;

	private final Context context;

	LocalContextStackImpl(Writer out, OutputFormatter defaultFormater, EventPublisher eventPublisher, Context context, Keywords keywords) {
		this.keywords = keywords;
		this.out = out;
		this.context = context;
		this.eventPublisher = eventPublisher;
		this.rootLocalContext = new LocalContextImpl(null, ROOT_LOCAL_CONTEXT_NAME, null, context, out, keywords);
		this.rootLocalContext.setGeneralOutputFormatter(defaultFormater);
		this.localContextStack.push(this.rootLocalContext);
	}

	private final Stack localContextStack = new LinkedStack();

	private final LocalContext rootLocalContext;

	private final Keywords keywords;

	public LocalContext getRootLocalContext() {
		return rootLocalContext;
	}

	public void pushLocalContext() {
		pushLocalContext(null, null);
	}

	public void pushLocalContext(String name) {
		pushLocalContext(name, null);
	}

	public void pushLocalContext(Map variablesContainer) {
		pushLocalContext(null, variablesContainer);
	}

	public void pushLocalContext(String name, Map variablesContainer) {
		LocalContext previous = getCurrentLocalContext();
		LocalContext localContext = new LocalContextImpl(previous, name, variablesContainer, context, out, keywords);
		localContextStack.push(localContext);
		eventPublisher.publishEvent(new LocalContextPushedEvent(this, previous, localContext));
	}

	public void popLocalContext() {
		LocalContext previous = (LocalContext)localContextStack.pop();
		try {
			eventPublisher.publishEvent(new LocalContextPopedEvent(this, previous, getCurrentLocalContext()));
		} finally {
			previous.clear();
		}
	}

	public LocalContext getCurrentLocalContext() {
		if (localContextStack.isEmpty())
			return rootLocalContext;
		return (LocalContext)localContextStack.peek();
	}

	public LocalContext findLocalContext(String name) {
		Assert.assertNotEmpty(name, "LocalContextStackImpl.context.name.required");
		LocalContext result = null;
		// 因LinkedStack使用LinkedList, 从头开始迭代快于倒序get()
		if (! localContextStack.isEmpty()) {
			for (Iterator iterator = localContextStack.iterator(); iterator.hasNext();) {
				LocalContext localContext = (LocalContext)iterator.next();
				if (localContext != null
						&& name.equals(localContext.getLocalContextName()))
					result = localContext;
			}
		}
		return result;
	}

	public Iterator getLocalContextStackValues() {
		return localContextStack.iterator();
	}

	public int getLocalContextStackSize() {
		return localContextStack.size();
	}

	public void clearLocalContexts() {
		while (! localContextStack.isEmpty())
			popLocalContext();
		localContextStack.clear();
	}

}
