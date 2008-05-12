package org.commontemplate.standard.operator.context;

import org.commontemplate.core.LocalContext;
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
		super(LocalContext.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return ((LocalContext)leftOperand).getVariable((String)rightOperand);
	}

}