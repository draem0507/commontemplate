package org.commontemplate.tools.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.commontemplate.core.Context;
import org.commontemplate.tools.ContextProxy;

/**
 * Web扩展上下文
 *
 * @author liangfei0201@163.com
 *
 */
public class WebContext extends ContextProxy {

	private static final long serialVersionUID = 1L;

	public static final String REQUEST_KEY = "request";

	public static final String RESPONSE_KEY = "response";

	private transient HttpServletRequest request;

	private transient HttpServletResponse response;

	public WebContext(Context context,
			HttpServletRequest request,
			HttpServletResponse response) {
		super(context);
		this.request = request;
		this.response = response;
		context.pushLocalContext();
		context.putReadonlyVariable("global", context.getGlobalContext());
		context.putReadonlyVariable("request", request);
		context.putReadonlyVariable("response", response);
		context.putReadonlyVariable("session", getSession());
		context.putReadonlyVariable("servletContext", getServletContext());
		// 用于内部处理 ----
		context.putProperty(REQUEST_KEY, request);
		context.putProperty(RESPONSE_KEY, response);
	}

	public HttpServletRequest getRequest() {
		//return (HttpServletRequest)context.lookupObject(REQUEST_KEY);
		return request;
	}

	public HttpServletResponse getResponse() {
		//return (HttpServletResponse)context.lookupObject(RESPONSE_KEY);
		return response;
	}

	public HttpSession getSession() {
		HttpServletRequest request = getRequest();
		if (request == null)
			return null;
		return request.getSession();
	}

	public ServletContext getServletContext() {
		HttpSession session = getSession();
		if (session == null)
			return null;
		return session.getServletContext();
	}

}
