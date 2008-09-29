package org.commontemplate.tools.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.commontemplate.util.Assert;

public class FileClassResourceLoader implements ResourceLoader {

	private final ResourceLoader fileResourceLoader; // 文件系统资源加载

	private final ResourceLoader classLoaderResourceLoader; // 类加载器资源加载

	private final ResourceLoader engineResourceLoader; // 模板引擎资源加载，考虑到jar包可能和项目被不同的ClassLoader所加载

	public FileClassResourceLoader() {
		this(Thread.currentThread().getContextClassLoader());
	}

	public FileClassResourceLoader(ClassLoader classLoader) {
		Assert.assertNotNull(classLoader, "ComboResourceLoader.class.loader.required");
		this.fileResourceLoader = new FileResourceLoader();
		this.classLoaderResourceLoader = new ClassLoaderResourceLoader(classLoader);
		this.engineResourceLoader = new ClassLoaderResourceLoader(ServletClassResourceLoader.class.getClassLoader());
	}

	private ResourceLoader getResourceLoader(String name) throws IOException {
		Assert.assertNotEmpty(name, "ComboResourceLoader.resource.name.required");
		name = name.trim();
		if (name.startsWith("org/commontemplate/") || name.startsWith("org\\commontemplate\\"))
			return engineResourceLoader;
		else if (new File(name).exists())
			return fileResourceLoader;
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
