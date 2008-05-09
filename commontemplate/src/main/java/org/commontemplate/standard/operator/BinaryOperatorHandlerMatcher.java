package org.commontemplate.standard.operator;

import org.commontemplate.config.BinaryOperatorHandler;

/**
 * 二元操作符匹配接口
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperatorHandlerMatcher extends BinaryOperatorHandler {

	/**
	 * 判断操作符是否匹配操作数
	 *
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @return 是否匹配
	 */
	public abstract boolean isMatch(Object leftOperand, Object rightOperand);

}
