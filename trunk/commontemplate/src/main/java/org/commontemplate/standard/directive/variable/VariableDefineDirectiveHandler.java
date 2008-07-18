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
		if (param instanceof Entry) {
			Entry model = (Entry)param;
			String var = String.valueOf(model.getKey());
			Object value = model.getValue();
			context.putVariable(var, value);
		} else {
			// TODO 待实现指定区域赋值
		}
	}

}