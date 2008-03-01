package org.commontemplate.standard.operator;

/**
 * 二元操作符类型匹配基类.
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class BinaryOperatorHandlerSupport implements BinaryOperatorHandlerMatcher, java.io.Serializable {

	private Class leftOperandClass;

	private Class rightOperandClass;

	private boolean operandNullable;
	
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
		this.leftOperandClass = leftOperandClass;
		this.rightOperandClass = rightOperandClass;
		this.operandNullable = operandNullable;
	}
	
	public boolean isMatch(Object leftOperand, Object rightOperand) {
		return (((leftOperand == null || rightOperand == null) && operandNullable)
				|| (leftOperand != null&& rightOperand != null
						&& leftOperandClass.isInstance(leftOperand) 
						&& rightOperandClass.isInstance(rightOperand)));
	}

}
