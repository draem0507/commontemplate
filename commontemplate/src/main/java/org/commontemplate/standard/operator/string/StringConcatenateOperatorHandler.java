package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * toString字符串连接符处理器
 * 
 * @author liangfei0201@163.com
 *
 */
public class StringConcatenateOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringConcatenateOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return getString(leftOperand) + getString(rightOperand);
	}
	
	private String getString(Object operand) {
		if (operand == null) 
			return "";
		return operand.toString();
	}

}