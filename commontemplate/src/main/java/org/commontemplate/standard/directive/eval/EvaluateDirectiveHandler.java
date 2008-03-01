package org.commontemplate.standard.directive.eval;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;

/**
 * 表达式解析指令. 如: $eval{"1+2"}
 * 
 * @author liangfei0201@163.com
 *
 */
public class EvaluateDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;
	
	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Expression expression = context.parseExpression((String)param);
		if (expression != null)
			context.output(expression.evaluate(context));
	}

}