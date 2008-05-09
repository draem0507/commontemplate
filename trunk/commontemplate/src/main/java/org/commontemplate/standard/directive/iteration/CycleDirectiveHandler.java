package org.commontemplate.standard.directive.iteration;

import java.util.List;
import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
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
		Entry params = (Entry)param;
		String var = params.getKey().toString();
		List list = (List)params.getValue();
		context.defineVariable(var, new Cycle(list));
	}

}
