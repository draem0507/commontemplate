package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.UndefinedException;

/**
 * 默认值定义指令. 当变量为空时赋值.
 * 
 * @author liangfei0201@163.com
 *
 */
public class VariableInitializeDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry model = (Entry)param;
		String var = model.getKey().toString();
		Object value = model.getValue();
		if (context.lookupVariable(var) == null) {
			try {
				context.assignVariable(var, value);
			} catch (UndefinedException e) {// 如果未定义则创建
				context.defineVariable(var, value);
			}
		}
	}

}