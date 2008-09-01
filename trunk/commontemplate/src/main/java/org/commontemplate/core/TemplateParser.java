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
	 * @param resource
	 *            模板源
	 * @return 模板
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public Template parseTemplate(Resource resource) throws ParsingException, IOException;

	/**
	 * 将字符串解析为匿名模板
	 *
	 * @param template
	 *            模板内容
	 * @return 匿名模板
	 * @throws ParsingException
	 *             模板解析出错时抛出
	 */
	public Template parseTemplate(String template) throws ParsingException;

	/**
	 * 获取模块构建器
	 *
	 * @param templateName 模板名称
	 * @return 模块构建器
	 */
	public TemplateBudiler getTemplateBudiler(String templateName);
}
