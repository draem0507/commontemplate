package org.commontemplate.core;

/**
 * 模板构建器, 为外部引擎或存储方式提供模板构建接口, 适合于自顶向下的构建方式.
 * (非线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public interface TemplateBudiler {

	/**
	 * 获取构建结果模板
	 *
	 * @return 模板
	 */
	public Template getTemplate();

	/**
	 * 开始块指令
	 *
	 * @param directiveName 指令名
	 * @param expression 指令表达式
	 */
	public void beginBlockDirective(String directiveName, Expression expression);

	/**
	 * 结束块指令
	 */
	public void endBlockDirective();

	/**
	 * 添加指令
	 *
	 * @param directiveName 指令名
	 * @param expression 指令表达式
	 */
	public void addDirective(String directiveName, Expression expression);

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

}
