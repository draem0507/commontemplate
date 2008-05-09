package org.commontemplate.config;

import java.io.Serializable;

/**
 * 一元操作符处理器 (实现类应保证线程安全，及友好的序列化，并且不应该修改操作数的状态)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class UnaryOperatorHandler implements Serializable {

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

	/**
	 * 一元操作符操作数延迟求值 <p/> doEvaluate的operand参数将会以LazyOperand的实例传入<br/>
	 * 可以用((LazyOperand)operand).evaluate()取真实值<br/>
	 *
	 * @see org.commontemplate.config.LazyOperand
	 * @return 是否延迟求值
	 *
	 */
	public boolean isOperandLazy() {
		return false;
	}

	/**
	 * 一元操作符操作数变量字面化 <p/> 当操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化
	 *
	 */
	public boolean isOperandNamed() {
		return false;
	}

	/**
	 * 一元操作符操作数函数字面化 <p/>
	 * 当操作数为函数时，将不作为函数运行，而作为org.commontemplate.util.Function传入
	 *
	 * @return 函数是否字面化
	 */
	public boolean isOperandFunctioned() {
		return false;
	}

}
