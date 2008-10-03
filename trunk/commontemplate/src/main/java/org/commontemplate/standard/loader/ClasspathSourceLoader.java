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
public class ClasspathSourceLoader extends URLSourceLoader {

	private final ClassLoader classLoader;

	public ClasspathSourceLoader() {
		this.classLoader = Thread.currentThread().getContextClassLoader();
	}

	public ClasspathSourceLoader(ClassLoader classLoader) {
		Assert.assertNotNull(classLoader, "ClasspathResourceLoader.class.loader.required");
		this.classLoader = classLoader;
	}

	protected URL getURL(String name) throws IOException {
		if (name.charAt(0) == '/' || name.charAt(0) == '\\')
			name = name.substring(1);
		return classLoader.getResource(name);
	}

}
