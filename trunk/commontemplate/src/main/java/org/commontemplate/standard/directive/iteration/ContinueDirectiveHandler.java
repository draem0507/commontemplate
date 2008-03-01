package org.commontemplate.standard.directive.iteration;


import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.util.TypeUtils;
import org.commontemplate.core.Context;

/**
 * 循环继续指令, 用于<code>ForeachDirectiveHandler<code>内部
 * 
 * @author liangfei0201@163.com
 *
 */
public class ContinueDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (TypeUtils.isTrue(param)) {
			throw new ContinueException();
		}
	}

}
