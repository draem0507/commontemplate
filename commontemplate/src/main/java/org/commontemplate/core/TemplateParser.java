package org.commontemplate.core;

import java.io.IOException;

/**
 * 模板指令解释总接口 <p/> (线程安全)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface TemplateParser extends ExpressionParser, TemplateFactory {

	/**
	 * 将模板源解析为模板
	 * 
	 * @param source
	 *            模板源
	 * @return 模板，后验条件不返回null
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public Template parseTemplate(Source source) throws ParsingException,
			IOException;

	/**
	 * 将字符串解析为匿名模板
	 * 
	 * @param template
	 *            模板内容
	 * @return 匿名模板，后验条件不返回null
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 */
	public Template parseTemplate(String template) throws ParsingException;

	/**
	 * 将字符串解析为模板
	 * 
	 * @param name
	 *            名称
	 * @param template
	 *            模板内容
	 * @return 模板
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 */
	public Template parseTemplate(String name, String template)
			throws ParsingException;

	/**
	 * 创建模块构建器
	 * 
	 * @return 模块构建器
	 */
	public TemplateBudiler createTemplateBudiler();
}
