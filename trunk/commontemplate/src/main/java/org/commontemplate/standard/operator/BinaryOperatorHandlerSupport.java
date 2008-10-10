package org.commontemplate.standard.operator;

/**
 * 二元操作符类型匹配基类.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperatorHandlerSupport extends BinaryOperatorHandlerMatcher {

	private static final long serialVersionUID = 1L;
	
	private Class[] leftOperandClass;

	private Class[] rightOperandClass;

	private boolean leftOperandNullable;

	private boolean rightOperandNullable;

	private boolean operandSwapable;

	/**
	 * 默认不能处理null
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 */
	public BinaryOperatorHandlerSupport(Class leftOperandClass,
			Class rightOperandClass) {
		this(leftOperandClass, rightOperandClass, false);
	}

	/**
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 * @param operandNullable 当前处理器是否能处理null
	 */
	public BinaryOperatorHandlerSupport(Class leftOperandClass,
			Class rightOperandClass, boolean operandNullable) {
		this(leftOperandClass, rightOperandClass, operandNullable, operandNullable);
	}

	/**
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 * @param leftOperandNullable 当前处理器左参是否能处理null
	 * @param rightOperandNullable 当前处理器右参是否能处理null
	 */
	public BinaryOperatorHandlerSupport(Class leftOperandClass,
			Class rightOperandClass, boolean leftOperandNullable, boolean rightOperandNullable) {
		this(leftOperandClass, rightOperandClass, leftOperandNullable, rightOperandNullable, false);
	}

	/**
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 * @param leftOperandNullable 当前处理器左参是否能处理null
	 * @param rightOperandNullable 当前处理器右参是否能处理null
	 * @param operandSwapable 参数是否可交换
	 */
	public BinaryOperatorHandlerSupport(Class leftOperandClass,
			Class rightOperandClass, boolean leftOperandNullable, boolean rightOperandNullable, boolean operandSwapable) {
		this(new Class[]{leftOperandClass}, new Class[]{rightOperandClass}, leftOperandNullable, rightOperandNullable, operandSwapable);
	}

	/**
	 * 默认不能处理null
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 */
	public BinaryOperatorHandlerSupport(Class[] leftOperandClass,
			Class[] rightOperandClass) {
		this(leftOperandClass, rightOperandClass, false);
	}

	/**
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 * @param operandNullable 当前处理器是否能处理null
	 */
	public BinaryOperatorHandlerSupport(Class[] leftOperandClass,
			Class[] rightOperandClass, boolean operandNullable) {
		this(leftOperandClass, rightOperandClass, operandNullable, operandNullable);
	}

	/**
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 * @param leftOperandNullable 当前处理器左参是否能处理null
	 * @param rightOperandNullable 当前处理器右参是否能处理null
	 */
	public BinaryOperatorHandlerSupport(Class[] leftOperandClass,
			Class[] rightOperandClass, boolean leftOperandNullable, boolean rightOperandNullable) {
		this(leftOperandClass, rightOperandClass, leftOperandNullable, rightOperandNullable, false);
	}

	/**
	 * @param leftOperandClass 当前处理器能处理的左参类型
	 * @param rightOperandClass 当前处理器能处理的右参类型
	 * @param leftOperandNullable 当前处理器左参是否能处理null
	 * @param rightOperandNullable 当前处理器右参是否能处理null
	 * @param operandSwapable 参数是否可交换
	 */
	public BinaryOperatorHandlerSupport(Class[] leftOperandClass,
			Class[] rightOperandClass, boolean leftOperandNullable, boolean rightOperandNullable, boolean operandSwapable) {
		this.leftOperandClass = leftOperandClass;
		this.rightOperandClass = rightOperandClass;
		this.leftOperandNullable = leftOperandNullable;
		this.rightOperandNullable = rightOperandNullable;
		this.operandSwapable = operandSwapable;
	}

	public boolean isMatch(Object leftOperand, Object rightOperand) {
		if (isMatchOperand(leftOperand, leftOperandClass, leftOperandNullable)
				&& isMatchOperand(rightOperand, rightOperandClass, rightOperandNullable))
			return true;
		if (operandSwapable)
			return isMatchOperand(rightOperand, leftOperandClass, leftOperandNullable)
				&& isMatchOperand(leftOperand, rightOperandClass, rightOperandNullable);
		return false;
	}

	private boolean isMatchOperand(Object operand, Class[] operandClass, boolean operandNullable) {
		if (operand == null)
			return operandNullable;
		for (int i = 0, n = operandClass.length; i < n; i ++)
			if (operandClass[i].isInstance(operand))
				return true;
		return false;
	}

}
