package org.commontemplate.tools.web;

import javax.servlet.http.HttpServletRequest;

import org.commontemplate.util.MapSupport;

import java.util.Enumeration;

/**
 * Request属性Map封装
 * 
 * @author liangfei0201@163.com
 *
 */
public class RequestMap extends MapSupport {

	private static final long serialVersionUID = 1L;
	
	private final HttpServletRequest request;

	public RequestMap(HttpServletRequest request) {
		if (request == null) 
			throw new java.lang.NullPointerException("request is null!");
		
		this.request = request;
	}
	
	protected Enumeration getNames() {
		return request.getAttributeNames();
	}

	protected Object getValue(Object key) {
		return request.getAttribute(String.valueOf(key));
	}
}
