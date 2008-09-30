package org.commontemplate.standard.loader;

import java.io.IOException;
import java.net.URL;

import org.commontemplate.core.Source;
import org.commontemplate.util.I18nExceptionFactory;

/**
 * URL模板源加载器
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class URLResourceLoader extends AbstractSourceLoader {

	public Source loadResource(String path, String name, String encoding)
			throws IOException {
		URL url = getURL(path);
		if (url == null)
			throw I18nExceptionFactory.createFileNotFoundException("URLResourceLoader.template.not.found", new Object[]{path, name});
		return new URLResource(url, name, encoding);
	}

	// 通过模板名获取指向该模板的URL
	protected abstract URL getURL(String name) throws IOException;

}
