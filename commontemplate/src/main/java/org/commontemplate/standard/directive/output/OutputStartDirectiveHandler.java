package org.commontemplate.standard.directive.output;

import java.util.List;

import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 信息输出指令.
 *
 * @author liangfei0201@163.com
 *
 */
public class OutputStartDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		context.output(param);
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
