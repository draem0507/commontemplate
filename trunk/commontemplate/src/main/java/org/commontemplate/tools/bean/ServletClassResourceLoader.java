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
public class ServletClassResourceLoader implements ResourceLoader {

	private final ResourceLoader servletContextResourceLoader; // Servlet上下文资源加载

	private final ResourceLoader classLoaderResourceLoader; // 类加载器资源加载

	private final ResourceLoader engineResourceLoader; // 模板引擎资源加载，考虑到jar包可能和项目被不同的ClassLoader所加载

	public ServletClassResourceLoader(final ServletContext servletContext) {
		Assert.assertNotNull(servletContext, "ComboResourceLoader.servlet.context.required");
		this.servletContextResourceLoader = new ServletContextResourceLoader(servletContext);
		this.classLoaderResourceLoader = new ClassLoaderResourceLoader();
		this.engineResourceLoader = new ClassLoaderResourceLoader(ServletClassResourceLoader.class.getClassLoader());
	}

	public ServletClassResourceLoader(final ServletContext servletContext, ClassLoader classLoader) {
		Assert.assertNotNull(servletContext, "ComboResourceLoader.servlet.context.required");
		Assert.assertNotNull(classLoader, "ComboResourceLoader.class.loader.required");
		this.servletContextResourceLoader = new ServletContextResourceLoader(servletContext);
		this.classLoaderResourceLoader = new ClassLoaderResourceLoader(classLoader);
		this.engineResourceLoader = new ClassLoaderResourceLoader(ServletClassResourceLoader.class.getClassLoader());
	}

	private ResourceLoader getResourceLoader(String name) throws IOException {
		Assert.assertNotEmpty(name, "ComboResourceLoader.resource.name.required");
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
