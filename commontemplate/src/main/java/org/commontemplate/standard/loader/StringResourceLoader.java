package org.commontemplate.standard.loader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.Resource;
import org.commontemplate.util.Assert;
import org.commontemplate.util.UrlUtils;

/**
 * 字符串模板源加载器
 *
 * @author liangfei0201@163.com
 *
 */
public class StringResourceLoader extends AbstractResourceLoader {

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
		this.resources.put(name, new StringResource(name, source));
	}

	protected Resource loadResource(String path, String name, String encoding)
			throws IOException {
		Resource resource = (Resource) resources.get(name);
		if (resource == null)
			throw new IOException("Not fount resouce: " + path);
		return resource;
	}

}