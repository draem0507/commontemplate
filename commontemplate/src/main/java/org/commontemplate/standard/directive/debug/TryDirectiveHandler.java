package org.commontemplate.standard.directive.debug;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;

public class TryDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	// 条件状态位，用于传递整个If链是否已经为true
	public static final String TRY_STATUS = "try.status";

	protected void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		LocalContext superLocalContext = context.getParentLocalContext();
		try {
			DirectiveUtils.renderAll(innerElements, context);
			superLocalContext.setStatus(TRY_STATUS, null);
		} catch (Throwable t) {
			superLocalContext.setStatus(TRY_STATUS, t);
		}
	}

}
