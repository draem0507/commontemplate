package org.commontemplate.standard.directive.output;

import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.Context;

/**
 * 信息输出指令.
 * 
 * @author liangfei0201@163.com
 *
 */
public class OutputStartDirectiveHandler implements BlockDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		context.output(param);
	}

}
