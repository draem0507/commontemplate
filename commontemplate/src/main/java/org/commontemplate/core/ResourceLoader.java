package org.commontemplate.core;

import java.io.IOException;
import java.util.Locale;

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
	 * @return 模板源，(注：后验条件不返回null，不存在时抛异常)
	 * @throws IOException
	 *             当模板不存在时抛出
	 */
	public Resource getResource(String name) throws IOException;

	/**
	 * 通过模板名获取相应模板源, 并指定加载编码方式
	 *
	 * @param name
	 *            模板名
	 * @param encoding
	 *            加载编码方式
	 * @return 模板源，(注：后验条件不返回null，不存在时抛异常)
	 * @throws IOException
	 *             当模板不存在时抛出
	 */
	public Resource getResource(String name, String encoding)
			throws IOException;

	/**
	 * 通过模板名获取相应模板源
	 *
	 * @param name
	 *            模板名
	 * @param locale
	 *            国际化区域信息
	 * @return 模板源，(注：后验条件不返回null，不存在时抛异常)
	 * @throws IOException
	 *             当模板不存在时抛出
	 */
	public Resource getResource(String name, Locale locale) throws IOException;

	/**
	 * 通过模板名获取相应模板源, 并指定加载编码方式
	 *
	 * @param name
	 *            模板名
	 * @param locale
	 *            国际化区域信息
	 * @param encoding
	 *            加载编码方式
	 * @return 模板源，(注：后验条件不返回null，不存在时抛异常)
	 * @throws IOException
	 *             当模板不存在时抛出
	 */
	public Resource getResource(String name, Locale locale, String encoding)
			throws IOException;

}
