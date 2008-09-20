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
		Resource resource = loadResource(path, name, encoding);
		if (resource != null && resource.getReader() != null)
			return resource;
		throw new IOException("Not found resource: " + name);
	}

	public Resource getResource(String name, Locale locale) throws IOException {
		return getResource(name, locale, getDefaultEncoding());
	}

	public Resource getResource(String name, Locale locale, String encoding)
			throws IOException {
		if (locale != null) {
			try {
				return  getResource(getLanguageCountryName(name, locale.getLanguage(), locale.getCountry()));
			} catch (IOException e) {
				try {
					return getResource(getLanguageName(name, locale.getLanguage()));
				} catch (IOException e2) {
					return getResource(name, encoding);
				}
			}
		}
		return getResource(name, encoding);
	}

	private String getLanguageCountryName(String name, String language, String country) {
		int i = name.lastIndexOf('.');
		if (i > -1)
			return name.substring(0, i) + "_" + language + "_" + country + name.substring(i);
		return name + "_" + language + "_" + country;
	}

	private String getLanguageName(String name, String language) {
		int i = name.lastIndexOf('.');
		if (i > -1)
			return name.substring(0, i) + "_" + language + name.substring(i);
		return name + "_" + language;
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
