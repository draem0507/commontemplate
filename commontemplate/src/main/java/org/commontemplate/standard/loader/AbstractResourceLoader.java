package org.commontemplate.standard.loader;

import java.io.IOException;

import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;

public abstract class AbstractResourceLoader implements ResourceLoader {
	
	private String defaultEncoding;
	
	private String virtualDirectory;

	public Resource loadResource(String name) throws IOException {
		return loadResource(name, getDefaultEncoding());
	}
	
	public Resource loadResource(String name, String encoding) throws IOException {
		if (name == null) 
			throw new java.lang.NullPointerException("模板名称不能为空！");
		String path = name;
		if (getVirtualDirectory() != null)
			path = getVirtualDirectory() + path;
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

	public final String getVirtualDirectory() {
		return virtualDirectory;
	}

	public final void setVirtualDirectory(String virtualDirectory) {
		this.virtualDirectory = virtualDirectory;
	}

}
