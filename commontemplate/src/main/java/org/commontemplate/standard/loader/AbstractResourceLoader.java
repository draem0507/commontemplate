package org.commontemplate.standard.loader;

import java.io.IOException;

import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.util.Assert;

public abstract class AbstractResourceLoader implements ResourceLoader {

	private String defaultEncoding;

	private String rootDirectory;

	public Resource loadResource(String name) throws IOException {
		return loadResource(name, getDefaultEncoding());
	}

	public Resource loadResource(String name, String encoding) throws IOException {
		Assert.assertNotNull("AbstractResourceLoader.resource.name.required");
		String path = name;
		if (getRootDirectory() != null)
			path = getRootDirectory() + path;
		return loadResource(path, name, encoding);
	}

	protected abstract Resource loadResource(String path, String name, String encoding) throws IOException;

	public final String getDefaultEncoding() {
		if (defaultEncoding != null)
			return defaultEncoding;
		return System.getProperty("file.encoding");
	}

	public final void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	public final String getRootDirectory() {
		return rootDirectory;
	}

	public final void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

}
