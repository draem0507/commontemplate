package org.commontemplate.config;

import java.io.Serializable;

/**
 * 二元操作符处理器 (实现类应保证线程安全，及友好的序列化，并且不应该修改操作数的状态)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperatorHandler implements Serializable {

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
	 * @return 是否延迟求值
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
	 * @return 是否延迟求值
	 *
	 */
	public boolean isRightOperandLazy() {
		return false;
	}

	/**
	 * 二元操作符左操作数变量字面化 <p/> 当左操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化
	 *
	 */
	public boolean isLeftOperandNamed() {
		return false;
	}

	/**
	 * 二元操作符右操作数变量字面化 <p/> 当右操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化
	 *
	 */
	public boolean isRightOperandNamed() {
		return false;
	}

	/**
	 * 二元操作符右操作数变量字面化 <p/> 当右操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 *
	 * @return 是否字面化
	 *
	 */
	public boolean isRightOperandDotNamed() {
		return false;
	}

	/**
	 * 二元操作符右操作数函数字面化 <p/>
	 * 当右操作数为函数时，将不作为函数运行，而作为org.commontemplate.util.Function传入
	 *
	 * @return 函数是否字面化
	 */
	public boolean isRightOperandFunctioned() {
		return false;
	}

	/**
	 * 从右至左结合操作符标识
	 *
	 * @return 是否从右至左结合
	 */
	public boolean isRightToLeft() {
		return false;
	}

	/**
	 * 是否符合结合律 (用于优化)
	 *
	 * @return 是否符合结合律
	 */
	public boolean isAssociative() {
		return false;
	}

	/**
	 * 是否符合交换律 (用于优化)
	 *
	 * @return 是否符合交换律
	 */
	public boolean isCommutative() {
		return false;
	}

}
