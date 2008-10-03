package org.commontemplate.standard.loader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.Source;
import org.commontemplate.util.Assert;
import org.commontemplate.util.UrlUtils;

/**
 * 字符串模板源加载器
 *
 * @author liangfei0201@163.com
 *
 */
public class StringSourceLoader extends AbstractSourceLoader {

	private final Map resources = new HashMap();

	public boolean hasTemplate(String name) {
		return resources.containsKey(name);
	}

	public void addTemplate(String name, String source) {
		Assert.assertNotEmpty(name, "StringResourceLoader.template.name.required");
		Assert.assertNotEmpty(source, "StringResourceLoader.template.source.required");
		try {
			name = UrlUtils.cleanUrl(name);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e); // TODO 异常待定义
		}
		this.resources.put(name, new StringSource(name, source));
	}

	protected Source loadResource(String path, String name, String encoding)
			throws IOException {
		Source resource = (Source) resources.get(name);
		if (resource == null)
			throw new IOException("Not fount resouce: " + path);
		return resource;
	}

}