package org.commontemplate.core;

import java.io.IOException;
import java.util.Locale;

/**
 * 模板工厂 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public interface TemplateLoader extends ResourceLoader, TemplateParser {

	/**
	 * 通过名称获取模板. 使用默认编码加载
	 *
	 * @param name
	 *            模板名称
	 * @return 模板，(注：后验条件不返回null，不存在时抛异常)
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public Template getTemplate(String name) throws IOException,
			ParsingException;

	/**
	 * 通过名称获取模板. 并指定加载编码
	 *
	 * @param name
	 *            模板名称
	 * @param encoding
	 *            模板编码
	 * @return 模板，(注：后验条件不返回null，不存在时抛异常)
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public Template getTemplate(String name, String encoding)
			throws IOException, ParsingException;

	/**
	 * 通过名称获取模板. 使用默认编码加载
	 *
	 * @param name
	 *            模板名称
	 * @param locale
	 *            国际化区域
	 * @return 模板，(注：后验条件不返回null，不存在时抛异常)
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public Template getTemplate(String name, Locale locale) throws IOException,
			ParsingException;

	/**
	 * 通过名称获取模板. 并指定加载编码
	 *
	 * @param name
	 *            模板名称
	 * @param locale
	 *            国际化区域
	 * @param encoding
	 *            模板编码
	 * @return 模板，(注：后验条件不返回null，不存在时抛异常)
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public Template getTemplate(String name, Locale locale, String encoding)
			throws IOException, ParsingException;

}
