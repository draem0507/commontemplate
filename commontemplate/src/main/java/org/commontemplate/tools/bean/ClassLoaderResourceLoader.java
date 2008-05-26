package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.commontemplate.util.Assert;

/**
 * ClassLoader的ResourceLoader适配器
 *
 * @author liangfei0201@163.com
 *
 */
public class ClassLoaderResourceLoader implements ResourceLoader {

	private final ClassLoader classLoader;

	public ClassLoaderResourceLoader() {
		this.classLoader = Thread.currentThread().getContextClassLoader();
	}

	public ClassLoaderResourceLoader(final ClassLoader classLoader) {
		Assert.assertNotNull(classLoader, "ClassLoaderResourceLoader.class.loader.required");
		this.classLoader = classLoader;
	}

	public InputStream getResourceAsStream(String name) throws IOException {
		return classLoader.getResourceAsStream(name);
	}

	public URL getResource(String name) throws IOException {
		return classLoader.getResource(name);
	}

}
