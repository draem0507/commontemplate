package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.UndefinedException;

/**
 * 变量赋值指令.
 * 
 * @author liangfei0201@163.com
 *
 */
public class VariableAssignDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry model = (Entry)param;
		String var = model.getKey().toString();
		Object value = model.getValue();
		try {
			context.assignVariable(var, value);
		} catch (UndefinedException e) {// 如果未定义则创建
			context.defineVariable(var, value);
		}
	}

}