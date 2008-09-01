package org.commontemplate.config;

/**
 * 表达式过滤器
 *
 * @author liangfei0201@163.com
 *
 */
public interface ExpressionFilter {

	/**
	 * 过滤表达式
	 *
	 * @param text
	 *            原始表达式
	 * @return 过滤后的表达式
	 */
	public String filter(String text);

}
