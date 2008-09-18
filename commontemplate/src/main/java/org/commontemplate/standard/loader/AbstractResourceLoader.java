package org.commontemplate.standard.loader;

import java.io.IOException;
import java.util.Locale;

import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.util.Assert;

public abstract class AbstractResourceLoader implements ResourceLoader {

	private String defaultEncoding;

	private String rootDirectory;

	public Resource getResource(String name) throws IOException {
		return getResource(name, getDefaultEncoding());
	}

	public Resource getResource(String name, String encoding) throws IOException {
		Assert.assertNotNull(name, "AbstractResourceLoader.resource.name.required");
		String path = name;
		if (getRootDirectory() != null)
			path = getRootDirectory() + path;
		return loadResource(path, name, encoding);
	}

	public Resource getResource(String name, Locale locale) throws IOException {
		return getResource(name, locale, getDefaultEncoding());
	}

	public Resource getResource(String name, Locale locale, String encoding)
			throws IOException {
		if (locale != null) {
			try {
				Resource resource = getResource(getLanguageCountryName(name, locale));
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// ignore
			}
			try {
				Resource resource = getResource(getLanguageName(name, locale));
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// ignore
			}
		}
		return getResource(name, encoding);
	}

	private String getLanguageCountryName(String name, Locale locale) {
		int i = name.lastIndexOf('.');
		if (i > -1)
			return name.substring(0, i) + "_" + locale.getLanguage() + "_" + locale.getCountry() + name.substring(i);
		return name + "_" + locale.getLanguage() + "_" + locale.getCountry();
	}

	private String getLanguageName(String name, Locale locale) {
		int i = name.lastIndexOf('.');
		if (i > -1)
			return name.substring(0, i) + "_" + locale.getLanguage() + name.substring(i);
		return name + "_" + locale.getLanguage();
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
