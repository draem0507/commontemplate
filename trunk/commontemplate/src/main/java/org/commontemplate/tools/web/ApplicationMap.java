package org.commontemplate.tools.web;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.commontemplate.util.MapSupport;

/**
 * Application属性Map封装
 * 
 * @author liangfei0201@163.com
 *
 */
public class ApplicationMap extends MapSupport {

	private static final long serialVersionUID = 1L;
	
	private final ServletContext context;

	public ApplicationMap(ServletContext context) {
		if (context == null) 
			throw new java.lang.NullPointerException("context is null!");
		
		this.context = context;
	}

	public ApplicationMap(HttpServletRequest request) {
		if (request == null) 
			throw new java.lang.NullPointerException("request is null!");
		
		this.context = request.getSession().getServletContext();
	}

	protected Enumeration getNames() {
		return context.getAttributeNames();
	}

	protected Object getValue(Object key) {
		return context.getAttribute(String.valueOf(key));
	}

}
