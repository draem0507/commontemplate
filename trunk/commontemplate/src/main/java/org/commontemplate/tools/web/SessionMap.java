package org.commontemplate.tools.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.commontemplate.util.MapSupport;

import java.util.Enumeration;

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
		if (session == null) 
			throw new java.lang.NullPointerException("session is null!");
		
		this.session = session;
	}

	public SessionMap(HttpServletRequest request) {
		if (request == null) 
			throw new java.lang.NullPointerException("request is null!");
		
		this.session = request.getSession();
	}

	
	protected Enumeration getNames() {
		return session.getAttributeNames();
	}

	protected Object getValue(Object key) {
		return session.getAttribute(String.valueOf(key));
	}

}
