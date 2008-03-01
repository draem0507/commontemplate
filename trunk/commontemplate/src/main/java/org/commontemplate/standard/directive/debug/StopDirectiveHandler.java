package org.commontemplate.standard.directive.debug;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;

/**
 * 停止解析指令, 调试时使用.
 * 
 * @author liangfei0201@163.com
 *
 */
public class StopDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		throw new StopException();
	}

}
