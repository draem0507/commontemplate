package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;

/**
 * 变量定义指令.
 * 
 * @author liangfei0201@163.com
 *
 */
public class VariableDefineDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry model = (Entry)param;
		context.defineVariable(model.getKey().toString(), model.getValue());
	}

}