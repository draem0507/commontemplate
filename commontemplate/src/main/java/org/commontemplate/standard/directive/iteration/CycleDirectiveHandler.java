package org.commontemplate.standard.directive.iteration;

import java.util.List;
import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.VariableScopeUtils;
import org.commontemplate.standard.operator.string.NamePair;
import org.commontemplate.core.Context;

/**
 * 循环取值序列定义指令, 每次取值向后滚动.
 *
 * @author liangfei0201@163.com
 *
 */
public class CycleDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof Entry) {
			Entry model = (Entry)param;
			Object key = model.getKey();
			List list = (List)model.getValue();
			Object value = new Cycle(list);
			if (key instanceof NamePair) {
				VariableScopeUtils.putVariable(context, false, (NamePair)key, value);
			} else {
				String var = String.valueOf(key);
				context.putVariable(var, value);
			}
		} else {
			throw new IllegalStateException("$cycle指令参数错误!"); // TODO 未国际化
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
