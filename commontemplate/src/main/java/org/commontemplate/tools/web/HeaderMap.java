package org.commontemplate.tools.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.commontemplate.util.Assert;
import org.commontemplate.util.MapSupport;

public class HeaderMap extends MapSupport {

	private static final long serialVersionUID = 1L;

	private final HttpServletRequest request;

	public HeaderMap(HttpServletRequest request) {
		Assert.assertNotNull(request, "HeaderMap.request.required");
		this.request = request;
	}

	protected Enumeration getNames() {
		return request.getHeaderNames();
	}

	protected Object getValue(Object key) {
		return request.getHeader(String.valueOf(key));
	}

}
