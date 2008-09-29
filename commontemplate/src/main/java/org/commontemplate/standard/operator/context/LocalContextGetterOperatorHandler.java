package org.commontemplate.standard.operator.context;

import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableStorage;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 局部上下文变量获取操作符: "."<br/>
 * 如: ${context["session"].user}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class LocalContextGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public LocalContextGetterOperatorHandler() {
		super(VariableStorage.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		try {
			return ((VariableStorage)leftOperand).getVariable((String)rightOperand);
		} catch (UndefinedException e) {
			return null;
		}
	}

}