package org.commontemplate.config;

import java.io.Serializable;

/**
 * 一元操作符处理器 (实现类应保证线程安全，及友好的序列化，并且不应该修改操作数的状态)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface UnaryOperatorHandler extends Serializable {

	/**
	 * 处理一元操作符求值
	 * 
	 * @param operand
	 *            操作数
	 * 
	 * @return 求值结果
	 * @throws Exception
	 *             处理过程中的任意异常都应向上抛出, 由引擎统一处理
	 */
	public abstract Object doEvaluate(Object operand) throws Exception;

}
