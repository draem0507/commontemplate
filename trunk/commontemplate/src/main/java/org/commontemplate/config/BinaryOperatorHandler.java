package org.commontemplate.config;

import java.io.Serializable;

/**
 * 二元操作符处理器 (实现类应保证线程安全，及友好的序列化，并且不应该修改操作数的状态)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperatorHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 处理二元操作符求值
	 *
	 * @param leftOperand
	 *            左操作符
	 * @param rightOperand
	 *            右操作符
	 *
	 * @return 求值结果
	 * @throws Exception
	 *             处理过程中的任意异常都应向上抛出, 由引擎统一处理
	 */
	public abstract Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception;

	/**
	 * 二元操作符左操作数延迟求值 <p/> doEvaluate的leftOperand参数将会以LazyOperand的实例传入<br/>
	 * 可以用((LazyOperand)leftOperand).evaluate()取真实值<br/>
	 *
	 * @see org.commontemplate.config.LazyOperand
	 * @return 是否延迟求值，默认返回false
	 *
	 */
	public boolean isLeftOperandLazy() {
		return false;
	}

	/**
	 * 二元操作符右操作数延迟求值 <p/> doEvaluate的rightOperand参数将会以LazyOperand的实例传入<br/>
	 * 可以用((LazyOperand)rightOperand).evaluate()取真实值<br/>
	 *
	 * @see org.commontemplate.config.LazyOperand
	 * @return 是否延迟求值，默认返回false
	 *
	 */
	public boolean isRightOperandLazy() {
		return false;
	}

	/**
	 * 二元操作符左操作数变量字面化 <p/> 当左操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化，默认返回false
	 *
	 */
	public boolean isLeftOperandNamed() {
		return false;
	}

	/**
	 * 二元操作符右操作数变量字面化 <p/> 当右操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化，默认返回false
	 *
	 */
	public boolean isRightOperandNamed() {
		return false;
	}

	/**
	 * 二元操作符右操作数变量字面化 <p/> 当右操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化，默认返回false
	 *
	 */
	public boolean isRightOperandDotNamed() {
		return false;
	}

	/**
	 * 二元操作符右操作数函数字面化 <p/>
	 * 当右操作数为函数时，将不作为函数运行，而作为org.commontemplate.util.Function传入
	 *
	 * @return 函数是否字面化，默认返回false
	 */
	public boolean isRightOperandFunctioned() {
		return false;
	}

	/**
	 * 从右至左结合操作符标识
	 *
	 * @return 是否从右至左结合，默认返回false
	 */
	public boolean isRightToLeft() {
		return false;
	}

	/**
	 * 根据参数类型判断是否允许编译器优化.
	 *
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @return 是否允许编译器优化，默认返回isOptimize()的值
	 */
	public boolean isOptimize(Object leftOperand, Object rightOperand) {
		return isOptimize();
	}

	/**
	 * 是否允许编译器优化.
	 * 当操作数都为常量时，编译器会在编译期求值，并保存到表达式树，操符符应保证传入相同的参数，总是得到相同的结果.
	 *
	 * @return 是否允许编译器优化，默认返回true
	 */
	protected boolean isOptimize() {
		return true;
	}

	/**
	 * 是否符合结合律 (用于优化)
	 *
	 * @return 是否符合结合律，默认返回false
	 */
	public boolean isAssociative() {
		return false;
	}

	/**
	 * 是否符合交换律 (用于优化)
	 *
	 * @return 是否符合交换律，默认返回false
	 */
	public boolean isCommutative() {
		return false;
	}

}
