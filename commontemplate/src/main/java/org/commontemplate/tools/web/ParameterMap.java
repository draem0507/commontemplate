package org.commontemplate.tools.web;

import javax.servlet.http.HttpServletRequest;

import org.commontemplate.util.Assert;
import org.commontemplate.util.MapSupport;


import java.util.Enumeration;

/**
 * Parameter属性Map封装
 *
 * @author liangfei0201@163.com
 *
 */
public class ParameterMap extends MapSupport {

	private static final long serialVersionUID = 1L;

	private final HttpServletRequest request;

	public ParameterMap(HttpServletRequest request) {
		Assert.assertNotNull(request, "ParameterMap.request.required");
		this.request = request;
	}

	protected Enumeration getNames() {
		return request.getParameterNames();
	}

	protected Object getValue(Object key) {
		return request.getParameter(String.valueOf(key));
	}

}
