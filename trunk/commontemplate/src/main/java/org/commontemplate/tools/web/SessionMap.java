package org.commontemplate.tools.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.commontemplate.util.Assert;
import org.commontemplate.util.MapSupport;

/**
 * Session属性Map封装
 *
 * @author liangfei0201@163.com
 *
 */
public class SessionMap extends MapSupport {

	private static final long serialVersionUID = 1L;

	private final HttpSession session;

	public SessionMap(HttpSession session) {
		Assert.assertNotNull(session, "SessionMap.session.required");
		this.session = session;
	}

	public SessionMap(HttpServletRequest request) {
		Assert.assertNotNull(request, "SessionMap.request.required");
		this.session = request.getSession();
	}


	protected Enumeration getNames() {
		return session.getAttributeNames();
	}

	protected Object getValue(Object key) {
		return session.getAttribute(String.valueOf(key));
	}

}
