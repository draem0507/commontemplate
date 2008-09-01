package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;

/**
 * 默认值定义指令. 当变量为空时赋值.
 *
 * @author liangfei0201@163.com
 *
 */
public class VariableInitializeDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry model = (Entry)param;
		String var = model.getKey().toString();
		Object value = model.getValue();
		if (context.getVariable(var) == null) {
			context.putVariable(var, value);
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}