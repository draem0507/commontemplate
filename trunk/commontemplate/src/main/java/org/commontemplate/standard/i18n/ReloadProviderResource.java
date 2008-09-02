package org.commontemplate.standard.i18n;

import java.io.IOException;
import java.net.URL;

/**
 * 处理资源的接口。
 * @author YanRong
 *
 */
public interface ReloadProviderResource {
	/**
	 * 从指定的URL读取资源。
	 * @param url
	 * URL 对象。实现 ReloadResourceProvider 接口的对象负责传递这个变量。
	 * @param encoding
	 * 编码方式。
	 * @see ReloadResourceProvider
	 * @throws IOException
	 * 
	 */
	public void loadFromURL(URL url, String encoding) throws IOException;
	/**
	 * 根据 key 得到 value 的处理方法。
	 * @param key
	 * @return
	 */
	public Object handleGetObject(String key);
}
