package org.commontemplate.core;

/**
 * 指令表达式
 *
 * @author Achievo 梁飞
 *
 */
public abstract class Directive extends Element {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取指令的参数表达式
	 *
	 * @return 指令的参数表达式, 指令没有表达式时返时null.
	 */
	public abstract Expression getExpression();

}
