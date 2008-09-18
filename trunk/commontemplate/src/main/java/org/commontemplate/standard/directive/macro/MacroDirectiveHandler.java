package org.commontemplate.standard.directive.macro;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.TypeUtils;

public class MacroDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String MACRO_TYPE = "macro";

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		String var = (String)param;
		Assert.assertTrue(TypeUtils.isNamed(var), "MacroDirectiveHandler.invaild.macro.name", new Object[]{var});
		context.getRootLocalContext().putProperty(MACRO_TYPE, var, new Macro(innerElements, var));
	}

	public boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
