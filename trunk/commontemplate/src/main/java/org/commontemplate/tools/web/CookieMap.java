package org.commontemplate.tools.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.commontemplate.util.MapSupport;

import java.util.Enumeration;

/**
 * Cookie属性Map封装
 * 
 * @author liangfei0201@163.com
 *
 */
public class CookieMap extends MapSupport {

	private static final long serialVersionUID = 1L;
	
	private final Cookie[] cookies;

	public CookieMap(HttpServletRequest request) {
		if (request == null) 
			throw new java.lang.NullPointerException("request is null!");
		
		this.cookies = request.getCookies();
	}
	
	public CookieMap(Cookie[] cks) {
		if (cks != null) {
			// 保护性拷贝
			int n = cks.length;
			this.cookies = new Cookie[n];
			for (int i = 0; i < n; i ++) {
				this.cookies[i] = cks[i];
			}
		} else {
			this.cookies = new Cookie[0];
		}
	}
	
	protected Enumeration getNames() {
		return new CookieEnumerator(cookies == null ? new Cookie[0] : cookies);
	}

	protected Object getValue(Object key) {
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Cookie Enumerator Class
	 */
	private static final class CookieEnumerator implements Enumeration {
		
		private int i = 0;

		private final Cookie[] cookieArray;

		public CookieEnumerator(Cookie[] cookies) {
			this.cookieArray = cookies;
		}

		public boolean hasMoreElements() {
			return cookieArray != null && cookieArray.length > i;
		}

		public Object nextElement() {
			if (cookieArray == null)
				return null;
			Cookie element = cookieArray[i];
			i++;
			return element.getName();
		}
	}
}
