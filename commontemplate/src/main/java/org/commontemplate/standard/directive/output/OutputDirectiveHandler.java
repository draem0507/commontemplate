package org.commontemplate.standard.directive.output;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 信息输出指令. 忽略内部块.
 * 
 * @author liangfei0201@163.com
 *
 */
public class OutputDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	protected void doRender(Context context, String directiveName, Object param) throws Exception {
		context.output(param);
	}

}
