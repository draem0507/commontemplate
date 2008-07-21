package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
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
				NamePair pair = (NamePair)key;
				String scope = pair.getLeftName();
				String var = pair.getRightName();
				if ("global".equals(scope)) {
					context.getGlobalContext().putVariable(var, value);
				} else if ("root".equals(scope)) {
					context.getRootLocalContext().putVariable(var, value);
				} else if ("super".equals(scope)) {
					context.getSuperLocalContext().putVariable(var, value);
				} else if ("local".equals(scope) || "this".equals(scope)) {
					context.putVariable(var, value);
				} else {
					LocalContext localContext = context.findLocalContext(scope);
					if (localContext != null) {
						localContext.putVariable(var, value);
					} else {
						context.putVariable(var, value);
					}
				}
			} else {
				String var = String.valueOf(model.getKey());
				context.putVariable(var, value);
			}
		} else {
			throw new IllegalStateException("$var指令参数错误!"); // TODO 未国际化
		}
	}

}