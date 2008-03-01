package org.commontemplate.standard.operator;

import org.commontemplate.config.UnaryOperatorHandler;

/**
 * 一元操作符匹配接口
 * 
 * @author liangfei0201@163.com
 *
 */
public interface UnaryOperatorHandlerMatcher extends UnaryOperatorHandler {
	
	/**
	 * 判断操作符是否匹配操作数
	 * 
	 * @param operand 操作数
	 * @return 是否匹配
	 */
	boolean isMatch(Object operand);
	
}
