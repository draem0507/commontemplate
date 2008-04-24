package org.commontemplate.core;

/**
 * 指令
 *
 * @author Achievo 梁飞
 *
 */
public abstract class Directive extends Element {

	/**
	 * 得到该指令表达式
	 *
	 * @return 表达式
	 */
	public abstract Expression getExpression();

}
