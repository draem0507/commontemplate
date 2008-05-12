package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;

/**
 * 变量赋值指令.
 *
 * @author liangfei0201@163.com
 *
 */
public class VariableAssignDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry entry = (Entry)param;
		String key = String.valueOf(entry.getKey());
		Object value = entry.getValue();
		if (context.isVariableExisted(key))
			context.setVariable(key, value);
		else
			context.putVariable(key, value);
	}

}