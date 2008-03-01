package org.commontemplate.core;

import java.io.IOException;

/**
 * 模板加载器 <p/> (线程安全)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface ResourceLoader {

	/**
	 * 通过模板名获取相应模板源
	 * 
	 * @param name
	 *            模板名
	 * @return 模板源
	 * @throws IOException
	 *             当模板不存在时抛出
	 */
	public Resource loadResource(String name) throws IOException;

	/**
	 * 通过模板名获取相应模板源, 并指定加载编码方式
	 * 
	 * @param name
	 *            模板名
	 * @param encoding
	 *            加载编码方式
	 * @return 模板源
	 * @throws IOException
	 *             当模板不存在时抛出
	 */
	public Resource loadResource(String name, String encoding)
			throws IOException;

}
