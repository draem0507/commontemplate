package org.commontemplate.core;

/**
 * 二元操作符 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperator extends Operator {

	/**
	 * 获取左参数
	 *
	 * @return 左参数
	 */
	public abstract Expression getLeftOperand();

	/**
	 * 获取右参数
	 *
	 * @return 右参数
	 */
	public abstract Expression getRightOperand();

}
