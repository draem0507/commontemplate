package org.commontemplate.standard.operator;

import org.commontemplate.config.UnaryOperatorHandler;

/**
 * 一元操作符匹配接口
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class UnaryOperatorHandlerMatcher extends UnaryOperatorHandler {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 判断操作符是否匹配操作数
	 *
	 * @param operand 操作数
	 * @return 是否匹配
	 */
	public abstract boolean isMatch(Object operand);

}
