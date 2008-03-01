package org.commontemplate.standard.loader;

import java.io.IOException;
import java.net.URL;

import org.commontemplate.util.Assert;

/**
 * 远程模板源加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public class RemoteResourceLoader extends URLResourceLoader {

	private final String domain;

	public RemoteResourceLoader() {
		this.domain = null;
	}

	/**
	 * 初始化模板所在域 必须包含协议头, 如: http://等
	 * 
	 * @param domain
	 *            模板所在域
	 */
	public RemoteResourceLoader(String domain) {
		Assert.assertContain(domain, "://", "初始化域必须包含协议头, 如http://或file://等!");
		this.domain = domain;
	}

	/**
	 * 如果没有初始化模板所在域, name中必须包含协议头, 如：getURL("http://www.xxx.com/yyy.mtl")
	 */
	protected URL getURL(String name) throws IOException {
		if (domain != null) {
			new URL(domain + name);
		}
		return new URL(name);
	}

}
