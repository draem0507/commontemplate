package org.commontemplate.standard.operator.context;

import org.commontemplate.core.Context;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 选择局部上下文操作符: "[]"<br/>
 * 如: ${context["session"].user}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ContextGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ContextGetterOperatorHandler() {
		super(Context.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Context context = (Context)leftOperand;
		String scopeName = (String)rightOperand;
		if ("global".equals(scopeName))
			return context.getGlobalContext();
		if ("root".equals(scopeName))
			return context.getRootLocalContext();
		if ("super".equals(scopeName))
			return context.getParentLocalContext();
		return context.findLocalContext(scopeName);
	}

}