package org.commontemplate.core;

import java.util.List;

/**
 * 模板元素工厂, 为外部引擎或存储方式提供模板组装元素, 适合于自底向上的构建方式.
 *
 * @author liangfei0201@163.com
 *
 */
public interface ElementFactory {

	/**
	 * 创建注释
	 *
	 * @param comment 注释内容
	 * @return 注释
	 */
	public Comment createComment(String comment);

	/**
	 * 创建文本
	 *
	 * @param text 文本内容
	 * @return 文本
	 */
	public Text createText(String text);

	/**
	 * 创建行指令
	 *
	 * @param name 指令名
	 * @param expression 指令表达式
	 * @return 行指令
	 */
	public Directive createDirective(String name, Expression expression);

	/**
	 * 创建块指令
	 *
	 * @param name 指令名
	 * @param expression 指令表达式
	 * @param elements 块指令内部元素
	 * @return 块指令
	 */
	public BlockDirective createBlockDirective(String name, Expression expression, List elements);

}
