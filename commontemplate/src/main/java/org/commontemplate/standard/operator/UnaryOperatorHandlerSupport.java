package org.commontemplate.standard.operator;

/**
 * 一元操作符类型匹配基类.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class UnaryOperatorHandlerSupport extends UnaryOperatorHandlerMatcher {

	private static final long serialVersionUID = 1L;
	
	private Class[] operandClass;

	private boolean operandNullable;

	/**
	 * 默认不能处理null
	 * @param operandClass 当前处理器能处理的参数类型
	 */
	public UnaryOperatorHandlerSupport(Class operandClass) {
		this(operandClass, false);
	}

	/**
	 * @param operandClass 当前处理器能处理的参数类型
	 * @param operandNullable 当前处理器是否能处理null
	 */
	public UnaryOperatorHandlerSupport(Class operandClass, boolean operandNullable) {
		this(new Class[]{operandClass}, operandNullable);
	}

	/**
	 * 默认不能处理null
	 * @param operandClass 当前处理器能处理的参数类型
	 */
	public UnaryOperatorHandlerSupport(Class[] operandClass) {
		this(operandClass, false);
	}

	/**
	 * @param operandClass 当前处理器能处理的参数类型
	 * @param operandNullable 当前处理器是否能处理null
	 */
	public UnaryOperatorHandlerSupport(Class[] operandClass, boolean operandNullable) {
		this.operandClass = operandClass;
		this.operandNullable = operandNullable;
	}

	public boolean isMatch(Object operand) {
		if (operand == null)
			return operandNullable;
		for (int i = 0, n = operandClass.length; i < n; i ++)
			if (operandClass[i].isInstance(operand))
				return true;
		return false;
	}
}
