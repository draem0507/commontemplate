package org.commontemplate.config;

import java.io.Serializable;

/**
 * 一元操作符处理器 (实现类应保证线程安全，及友好的序列化，并且不应该修改操作数的状态)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class UnaryOperatorHandler implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	 * @return 是否延迟求值，默认返回false
	 *
	 */
	public boolean isOperandLazy() {
		return false;
	}

	/**
	 * 一元操作符操作数变量字面化 <p/> 当操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化，默认返回false
	 *
	 */
	public boolean isOperandNamed() {
		return false;
	}

	/**
	 * 一元操作符操作数变量字面化 <p/> 当操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化，默认返回false
	 *
	 */
	public boolean isOperandDotNamed() {
		return false;
	}

	/**
	 * 一元操作符操作数函数字面化 <p/>
	 * 当操作数为函数时，将不作为函数运行，而作为<code>org.commontemplate.util.Function</code>传入
	 *
	 * @return 函数是否字面化，默认返回false
	 */
	public boolean isOperandFunctioned() {
		return false;
	}

	/**
	 * 名称一元操作符是否为关键字
	 *
	 * @return 是否为关键字，默认返回false
	 */
	public boolean isKeyword() {
		return false;
	}

	/**
	 * 根据参数类型判断是否允许编译器优化.
	 *
	 * @param operand 左操作数
	 * @return 是否允许编译器优化，默认返回isOptimize()的值
	 */
	public boolean isOptimize(Object operand) {
		return isOptimize();
	}

	/**
	 * 是否允许编译器优化.
	 * 当操作数都为常量时，编译器会在编译期求值，并保存到表达式树，操符符应保证传入相同的参数，总是得到相同的结果.
	 *
	 * @return 是否允许编译器优化，默认返回true
	 */
	public boolean isOptimize() {
		return true;
	}

}
