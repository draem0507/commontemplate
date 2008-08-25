package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.VariableScopeUtils;
import org.commontemplate.standard.operator.string.NamePair;

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
			Object key = model.getKey();
			Object value = model.getValue();
			if (key instanceof NamePair) {
				VariableScopeUtils.putVariable(context, false, (NamePair)param, value);
			} else {
				String var = String.valueOf(model.getKey());
				context.putVariable(var, value);
			}
		} else {
			throw new IllegalStateException("$var指令参数错误!"); // TODO 未国际化
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}