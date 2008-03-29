package org.commontemplate.ext.directive.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;
import javax.servlet.jsp.tagext.BodyContent;

import org.apache.commons.el.ExpressionEvaluatorImpl;

import org.commontemplate.core.Context;
import org.commontemplate.util.IterEnumeration;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

/**
 * PageContext适配实现
 * 
 * @author liangfei0201@163.com
 *
 */
class PageContextImpl extends PageContext {

	private final Context context;
	
	private final VariableResolver variableResolver;

	private static final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluatorImpl(); // 采用common-el.jar的实现

	PageContextImpl(Context context) {
		this.context = context;
		variableResolver = new VariableResolver() {
			public Object resolveVariable(String name) throws ELException {
				return PageContextImpl.this.findAttribute(name);
			}
		};
	}

	public void initialize(Servlet servlet, ServletRequest request,
			ServletResponse response, String errorPageURL,
			boolean needsSession, int bufferSize, boolean autoFlush) {
		throw new UnsupportedOperationException();
	}

	public void release() {
		// do nothing
	}

	public void setAttribute(String name, Object value) {
		setAttribute(name, value, PAGE_SCOPE);
	}

	public void setAttribute(String name, Object value, int scope) {
		switch (scope) {
			case PAGE_SCOPE: {
				context.defineVariable(name, value);
				break;
			}
			case REQUEST_SCOPE: {
				getRequest().setAttribute(name, value);
				break;
			}
			case SESSION_SCOPE: {
				getSession().setAttribute(name, value);
				break;
			}
			case APPLICATION_SCOPE: {
				getServletContext().setAttribute(name, value);
				break;
			}
			default: {
				throw new IllegalArgumentException("Invalid scope " + scope);
			}
		}
	}

	public Object getAttribute(String name) {
		return getAttribute(name, PAGE_SCOPE);
	}

	public Object getAttribute(String name, int scope) {
		switch (scope) {
			case PAGE_SCOPE: {
				return context.lookupVariable(name);
			}
			case REQUEST_SCOPE: {
				return getRequest().getAttribute(name);
			}
			case SESSION_SCOPE: {
				HttpSession session = getSession();
				return session == null ? null : session.getAttribute(name);
			}
			case APPLICATION_SCOPE: {
				return getServletContext().getAttribute(name);
			}
			default: {
				throw new IllegalArgumentException("Invalid scope " + scope);
			}
		}
	}

	public Object findAttribute(String name) {
		Object result = getAttribute(name, PAGE_SCOPE);
		if (result != null)
			return result;
		result = getAttribute(name, REQUEST_SCOPE);
		if (result != null)
			return result;
		result = getAttribute(name, SESSION_SCOPE);
		if (result != null)
			return result;
		return getAttribute(name, APPLICATION_SCOPE);
	}

	public void removeAttribute(String name) {
		removeAttribute(name, PAGE_SCOPE);
		removeAttribute(name, REQUEST_SCOPE);
		removeAttribute(name, SESSION_SCOPE);
		removeAttribute(name, APPLICATION_SCOPE);
	}

	public void removeAttribute(String name, int scope) {
		switch (scope) {
			case PAGE_SCOPE: {
				context.removeVariable(name);
				break;
			}
			case REQUEST_SCOPE: {
				getRequest().removeAttribute(name);
				break;
			}
			case SESSION_SCOPE: {
				HttpSession session = getSession();
				if (session != null)
					session.removeAttribute(name);
				break;
			}
			case APPLICATION_SCOPE: {
				getServletContext().removeAttribute(name);
				break;
			}
			default: {
				throw new IllegalArgumentException("Invalid scope: " + scope);
			}
		}
	}

	public int getAttributesScope(String name) {
		if (getAttribute(name, PAGE_SCOPE) != null)
			return PAGE_SCOPE;
		if (getAttribute(name, REQUEST_SCOPE) != null)
			return REQUEST_SCOPE;
		if (getAttribute(name, SESSION_SCOPE) != null)
			return SESSION_SCOPE;
		if (getAttribute(name, APPLICATION_SCOPE) != null)
			return APPLICATION_SCOPE;
		return 0;
	}

	public Enumeration getAttributeNamesInScope(int scope) {
		switch (scope) {
			case PAGE_SCOPE: {
				return new IterEnumeration(context.getDefinedVariables()
						.keySet().iterator());
			}
			case REQUEST_SCOPE: {
				return getRequest().getAttributeNames();
			}
			case SESSION_SCOPE: {
				HttpSession session = getSession();
				if (session != null)
					return session.getAttributeNames();
				return Collections.enumeration(Collections.EMPTY_SET);
			}
			case APPLICATION_SCOPE: {
				return getServletContext().getAttributeNames();
			}
			default: {
				throw new IllegalArgumentException("Invalid scope " + scope);
			}
		}
	}

	public Object getPage() {
		return context.getCurrentTemplate();
	}

	public ServletRequest getRequest() {
		return (ServletRequest)context.lookupObject("request");
	}

	public ServletResponse getResponse() {
		return (ServletResponse)context.lookupObject("response");
	}

	public Exception getException() {
		throw new UnsupportedOperationException();
	}

	public ServletConfig getServletConfig() {
		throw new UnsupportedOperationException();
	}

	public HttpSession getSession() {
		return ((HttpServletRequest)getRequest()).getSession();
	}

	public ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	public void forward(String url) throws ServletException, IOException {
		getRequest().getRequestDispatcher(url).forward(getRequest(), getResponse());
	}

	public void include(String url) throws ServletException, IOException {
		getRequest().getRequestDispatcher(url).include(getRequest(), getResponse());
	}

	public void include(String url, boolean flush) throws ServletException, IOException {
		getRequest().getRequestDispatcher(url).include(getRequest(), getResponse());
	}
	
	public ExpressionEvaluator getExpressionEvaluator() {
		return expressionEvaluator;
	}

	public VariableResolver getVariableResolver() {
		return variableResolver;
	}

	public void handlePageException(Exception e) {
		throw new UnsupportedOperationException();
	}

	public void handlePageException(Throwable e) {
		throw new UnsupportedOperationException();
	}

	// 输出栈 ----

	public JspWriter getOut() {
		if (outStack.isEmpty())
			return convertToJspWriter(context.getOut());
		return (JspWriter) outStack.peek();
	}

	private Stack outStack = new LinkedStack();

	public BodyContent pushBody() {
		BodyContent bc = new BodyContentImpl(getOut(), true);
		outStack.push(bc);
		return bc;
	}

	public JspWriter pushBody(Writer out) {
		JspWriter jspWriter = convertToJspWriter(out);
		outStack.push(jspWriter);
		return jspWriter;
	}

	public JspWriter popBody() {
		return (JspWriter) outStack.pop();
	}
	
	private JspWriter convertToJspWriter(Writer out) {
		if (out instanceof JspWriter)
			return (JspWriter) out;
		return new JspWriterImpl(out);
	}

	// 标签栈 ----

	private Stack tagStack = new LinkedStack();

	void pushTag(Object tag) {
		tagStack.push(tag);
	}

	Object popTag() {
		return tagStack.pop();
	}

	Object findParentTag(Class tagClass) {
		for (Iterator i = tagStack.iterator(); i.hasNext();) {
			Object tag = i.next();
			if (tagClass.isInstance(tag))
				return tag;
		}
		return null;
	}

}
