package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 资源加载器接口
 * 
 * Hack:
 * JDK中多个类具有getResource, getResourceAsStream等函数，却没有公共的接口，
 * 在这里将它们适配为公共接口
 * 
 * @author liangfei0201@163.com
 *
 */
public interface ResourceLoader {
	
	/**
	 * 获取资源位置
	 * 
	 * @param name 资源名称
	 * @return 资源位置
	 * @throws IOException 资源不存在或读取失败时抛出
	 */
	public URL getResource(String name) throws IOException;

	/**
	 * 获取资源输入流
	 * 
	 * @param name 资源名称
	 * @return 资源输入流
	 * @throws IOException 资源不存在或读取失败时抛出
	 */
	public InputStream getResourceAsStream(String name) throws IOException;

}
