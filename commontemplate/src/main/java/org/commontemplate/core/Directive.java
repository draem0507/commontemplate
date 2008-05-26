package org.commontemplate.core;

/**
 * 指令
 *
 * @author Achievo 梁飞
 *
 */
public abstract class Directive extends Element {

	public static final String TYPE = "Directive";

	public String getType() {
		return TYPE;
	}

	/**
	 * 得到该指令表达式
	 *
	 * @return 表达式, 指令未定表达式时返时null.
	 */
	public abstract Expression getExpression();

}
