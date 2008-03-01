package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import org.commontemplate.util.Assert;

/**
 * ServletContext的ResourceLoader适配器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ServletContextResourceLoader implements ResourceLoader {
	
	private final ServletContext servletContext;
	
	public ServletContextResourceLoader(final ServletContext servletContext) {
		Assert.assertNotNull(servletContext, "servletContext 不能为空！");
		this.servletContext = servletContext;
	}

	public InputStream getResourceAsStream(final String name) throws IOException {
		return servletContext.getResourceAsStream(name);
	}
	
	public URL getResource(final String name) throws IOException {
		return servletContext.getResource(name);
	}

}
