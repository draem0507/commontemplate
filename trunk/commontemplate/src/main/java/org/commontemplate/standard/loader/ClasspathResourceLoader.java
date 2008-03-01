package org.commontemplate.standard.loader;

import java.io.IOException;
import java.net.URL;

import org.commontemplate.util.Assert;

/**
 * Classpath模板源加载器，用于从classpath中搜索模板源
 * 
 * @author liangfei0201@163.com
 *
 */
public class ClasspathResourceLoader extends URLResourceLoader {

	private final ClassLoader classLoader;

	public ClasspathResourceLoader() {
		this.classLoader = Thread.currentThread().getContextClassLoader();
	}

	public ClasspathResourceLoader(ClassLoader classLoader) {
		Assert.assertNotNull(classLoader, "类加载器不能为空！");
		this.classLoader = classLoader;
	}

	protected URL getURL(String name) throws IOException {
		if (name.charAt(0) == '/' || name.charAt(0) == '\\')
			name = name.substring(1);
		return classLoader.getResource(name);
	}

}
