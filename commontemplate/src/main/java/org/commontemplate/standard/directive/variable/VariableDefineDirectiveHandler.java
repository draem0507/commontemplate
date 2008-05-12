package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 变量定义指令.
 *
 * @author liangfei0201@163.com
 *
 */
public class VariableDefineDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry entry = (Entry)param;
		context.putVariable(String.valueOf(entry.getKey()), entry.getValue());
	}

}