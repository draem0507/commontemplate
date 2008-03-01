package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import org.commontemplate.util.Assert;

/**
 * 组合资源加载器.
 * <p/>
 * 通常用于Web应用配置加载，
 * 如果以"/"开头，则在运行项目的Web目录中查找，
 * 如果以"org/commontemplate/"开头，将在模板引擎的jar包内查找，
 * 否则在运行项目的ClassPath中查找，
 * 
 * @author liangfei0201@163.com
 *
 */
public class ComboResourceLoader implements ResourceLoader {
	
	private final ResourceLoader servletContextResourceLoader; // Servlet上下文资源加载
	
	private final ResourceLoader classLoaderResourceLoader; // 类加载器资源加载
	
	private final ResourceLoader engineResourceLoader; // 模板引擎资源加载，考虑到jar包可能和项目被不同的ClassLoader所加载
	
	public ComboResourceLoader(final ServletContext servletContext) {
		Assert.assertNotNull(servletContext, "servletContext 不能为空！");
		this.servletContextResourceLoader = new ServletContextResourceLoader(servletContext);
		this.classLoaderResourceLoader = new ClassLoaderResourceLoader();
		this.engineResourceLoader = new ClassLoaderResourceLoader(ComboResourceLoader.class.getClassLoader());
	}
	
	public ComboResourceLoader(final ServletContext servletContext, ClassLoader classLoader) {
		Assert.assertNotNull(servletContext, "servletContext 不能为空！");
		Assert.assertNotNull(classLoader, "classLoader 不能为空！");
		this.servletContextResourceLoader = new ServletContextResourceLoader(servletContext);
		this.classLoaderResourceLoader = new ClassLoaderResourceLoader(classLoader);
		this.engineResourceLoader = new ClassLoaderResourceLoader(ComboResourceLoader.class.getClassLoader());
	}
	
	private ResourceLoader getResourceLoader(String name) throws IOException {
		if (name == null || name.trim().length() == 0) 
			throw new IOException("无法查找null资源！");
		name = name.trim();
		if (name.charAt(0) == '/')
			return servletContextResourceLoader;
		else if (name.startsWith("org/commontemplate/") || name.startsWith("org\\commontemplate\\"))
			return engineResourceLoader;
		else
			return classLoaderResourceLoader;
	}

	public InputStream getResourceAsStream(String name) throws IOException {
		return getResourceLoader(name).getResourceAsStream(name);
	}
	
	public URL getResource(String name) throws IOException {
		return getResourceLoader(name).getResource(name);
	}

}
