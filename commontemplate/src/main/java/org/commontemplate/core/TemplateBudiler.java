package org.commontemplate.core;

/**
 * 模板构建器, 为外部引擎或存储方式提供模板构建接口, 适合于自顶向下的构建方式.
 * (非线程安全)
 *
 * @see org.commontemplate.core.TemplateParser#createTemplateBudiler()
 * @author liangfei0201@163.com
 *
 */
public interface TemplateBudiler {

	/**
	 * 设置模板名称
	 *
	 * @param templateName 模板名称
	 */
	public void setTemplateName(String templateName);

	/**
	 * 设置模板编码
	 *
	 * @param templateEncoding 模板编码
	 */
	public void setTemplateEncoding(String templateEncoding);

	/**
	 * 设置模板最后修改时间
	 *
	 * @param templateLastModified 模板最后修改时间
	 */
	public void setTemplateLastModified(long templateLastModified);

	/**
	 * 获取构建结果模板
	 *
	 * @return 模板
	 */
	public Template getTemplate();

	/**
	 * 添加文本
	 *
	 * @param text 文本内容
	 */
	public void addText(String text);

	/**
	 * 添加注释内容
	 *
	 * @param comment 注释内容
	 */
	public void addComment(String comment);

	/**
	 * 添加指令
	 *
	 * @param directiveName 指令名
	 * @param expression 指令表达式
	 */
	public void addDirective(String directiveName, Expression expression);

	/**
	 * 开始块指令
	 *
	 * @param directiveName 指令名
	 * @param expression 指令表达式
	 */
	public void addBlockDirective(String directiveName, Expression expression);

	/**
	 * 结束块指令
	 */
	public void endBlockDirective();

	/**
	 * 结束块指令, 并验证指令名
	 *
	 * @param directiveName 指令名
	 */
	public void endBlockDirective(String directiveName);

}
