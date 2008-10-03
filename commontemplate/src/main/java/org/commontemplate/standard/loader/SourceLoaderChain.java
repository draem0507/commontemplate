package org.commontemplate.standard.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.commontemplate.core.Source;
import org.commontemplate.core.SourceLoader;
import org.commontemplate.util.Assert;
import org.commontemplate.util.I18nExceptionFactory;

/**
 * 多模板源组合器
 *
 * @author liangfei0201@163.com
 *
 */
public class SourceLoaderChain implements SourceLoader {

	private List resourceLoaders;

	public void setResourceLoaders(List resourceLoaders) {
		Assert.assertNotEmpty(resourceLoaders, "ResourceLoaderChain.items.required");
		this.resourceLoaders = resourceLoaders;
	}

	public void addResourceLoader(SourceLoader resourceLoader) {
		if (this.resourceLoaders == null)
			this.resourceLoaders = new ArrayList();
		this.resourceLoaders.add(resourceLoader);
	}

	public Source getSource(String name, String encoding)
			throws IOException {
		for (Iterator iterator = resourceLoaders.iterator(); iterator.hasNext();) {
			try {
				SourceLoader resourceLoader = (SourceLoader)iterator.next();
				if (resourceLoader != null) {
					Source resource = resourceLoader.getSource(name, encoding);
					if (resource != null)
						return resource;
				}
			} catch (IOException e) {
				// 忽略，继续取下一loader
			}
		}
		throw I18nExceptionFactory.createFileNotFoundException("ResourceLoaderChain.resource.not.found", new Object[]{name});
	}

	public Source getSource(String name) throws IOException {
		for (Iterator iterator = resourceLoaders.iterator(); iterator.hasNext();) {
			try {
				SourceLoader resourceLoader = (SourceLoader)iterator.next();
				Source resource = resourceLoader.getSource(name);
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// 忽略，继续取下一loader
			}
		}
		throw I18nExceptionFactory.createFileNotFoundException("ResourceLoaderChain.resource.not.found", new Object[]{name});
	}

	public Source getSource(String name, Locale locale) throws IOException {
		for (Iterator iterator = resourceLoaders.iterator(); iterator.hasNext();) {
			try {
				SourceLoader resourceLoader = (SourceLoader)iterator.next();
				Source resource = resourceLoader.getSource(name, locale);
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// 忽略，继续取下一loader
			}
		}
		throw I18nExceptionFactory.createFileNotFoundException("ResourceLoaderChain.resource.not.found", new Object[]{name});
	}

	public Source getSource(String name, Locale locale, String encoding)
			throws IOException {
		for (Iterator iterator = resourceLoaders.iterator(); iterator.hasNext();) {
			try {
				SourceLoader resourceLoader = (SourceLoader)iterator.next();
				Source resource = resourceLoader.getSource(name, locale, encoding);
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// 忽略，继续取下一loader
			}
		}
		throw I18nExceptionFactory.createFileNotFoundException("ResourceLoaderChain.resource.not.found", new Object[]{name});
	}
	public String toString() {
		return resourceLoaders.toString();
	}

}
