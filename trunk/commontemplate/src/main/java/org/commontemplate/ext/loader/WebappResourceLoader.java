package org.commontemplate.ext.loader;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.commontemplate.standard.loader.URLResourceLoader;


/**
 * 网络应用文件加载器
 *
 * @author liangfei0201@163.com
 *
 */
public class WebappResourceLoader extends URLResourceLoader {

	private ServletContext servletContext;

	public WebappResourceLoader() {

	}

	public WebappResourceLoader(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	protected URL getURL(String name) throws IOException {
		if (name.charAt(0) != '/')
			name = "/" + name;
		return servletContext.getResource(name);
	}

}
