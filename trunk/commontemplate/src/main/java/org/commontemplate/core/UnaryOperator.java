package org.commontemplate.core;

/**
 * 一元操作符 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class UnaryOperator extends Operator {

	public static final String TYPE = "UnaryOperator";

	public String getType() {
		return TYPE;
	}

	/**
	 * 获取参数
	 *
	 * @return 参数
	 */
	public abstract Expression getOperand();

}
