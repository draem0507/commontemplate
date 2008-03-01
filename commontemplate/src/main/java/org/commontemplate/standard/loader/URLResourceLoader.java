package org.commontemplate.standard.loader;

import java.io.IOException;
import java.net.URL;

import org.commontemplate.core.Resource;

/**
 * URL模板源加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class URLResourceLoader extends AbstractResourceLoader {

	public Resource loadResource(String path, String name, String encoding)
			throws IOException {
		URL url = getURL(path);
		if (url == null)
			throw new IOException("找不到模板文件: " + path + "，模板名称：" + name);
		return new URLResource(url, name, encoding);
	}

	// 通过模板名获取指向该模板的URL
	protected abstract URL getURL(String name) throws IOException;

}
