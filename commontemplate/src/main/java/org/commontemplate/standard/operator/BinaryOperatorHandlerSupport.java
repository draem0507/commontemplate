package org.commontemplate.standard.operator;

/**
 * 二元操作符类型匹配基类.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperatorHandlerSupport extends BinaryOperatorHandlerMatcher {

	private Class leftOperandClass;

	private Class rightOperandClass;

	private boolean leftOperandNullable;

	private boolean rightOperandNullable;

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
		this.leftOperandClass = leftOperandClass;
		this.rightOperandClass = rightOperandClass;
		this.leftOperandNullable = leftOperandNullable;
		this.rightOperandNullable = rightOperandNullable;
	}

	public boolean isMatch(Object leftOperand, Object rightOperand) {
		return isMatchOperand(leftOperand, leftOperandClass, leftOperandNullable)
				&& isMatchOperand(rightOperand, rightOperandClass, rightOperandNullable);
	}

	private boolean isMatchOperand(Object operand, Class operandClass, boolean operandNullable) {
		if (operand == null)
			return operandNullable;
		return operandClass.isInstance(operand);
	}

}
