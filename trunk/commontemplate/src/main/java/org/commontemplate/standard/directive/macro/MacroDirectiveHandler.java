package org.commontemplate.standard.directive.macro;


import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.Context;

public class MacroDirectiveHandler implements BlockDirectiveHandler {

	private static final long serialVersionUID = 1L;
	
	public static final String MACRO_TYPE = "macro";

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		String var = (String)param;
		context.getSuperLocalContext().putObject(MACRO_TYPE, var, innerElements);
	}

}
