package org.commontemplate.standard.directive.macro;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.util.Assert;

public class MacroDefaultBlockDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String name,
			Object param, List innerElements) throws Exception {
		Map variables = ParameterUtils.getParameters(param);
		List macro = (List)context.getProperty(MacroDirectiveHandler.MACRO_TYPE, name);
		Assert.assertNotNull(macro, "MacroDefaultBlockDirectiveHandler.invaild.macro.name", new Object[]{name});
		Context newContext = context.createContext();
		newContext.putProperty(InnerDirectiveHandler.INNER_BLOCK, innerElements);
		if (variables.size() > 0)
			newContext.putAllVariables(variables);
		try {
			DirectiveUtils.renderAll(macro, newContext);
		} catch (ReturnException e) {
			// ignore
		} finally {
			newContext.clear();
		}
	}

}
