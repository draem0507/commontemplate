package org.commontemplate.standard.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.util.Assert;

/**
 * 字符串模板源加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public class StringResourceLoader implements ResourceLoader {

	private final Map resources = new HashMap();

	public StringResourceLoader() {

	}

	public boolean hasTemplate(String name) {
		return resources.containsKey(name);
	}

	public void addTemplate(String name, String source) {
		Assert.assertNotEmpty(name, "name == null");
		Assert.assertNotEmpty(source, "source == null");
		this.resources.put(name, new StringResource(name, source));
	}

	public Resource loadResource(String name, String encoding)
			throws IOException {
		return loadResource(name);
	}

	public Resource loadResource(String name) throws IOException {
		return (Resource) resources.get(name);
	}

}