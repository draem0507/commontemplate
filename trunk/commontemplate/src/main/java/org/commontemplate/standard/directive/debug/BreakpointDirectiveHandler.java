package org.commontemplate.standard.directive.debug;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.standard.debug.Breakpoint;
import org.commontemplate.standard.debug.DebugManager;

public class BreakpointDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, Directive directive) throws Exception {
		Breakpoint breakpoint = new Breakpoint(context.getCurrentTemplate().getName(), directive.getLocation().getBegin().getLine());
		DebugManager.getInstance().addBreakpoint(breakpoint);
	}

}
