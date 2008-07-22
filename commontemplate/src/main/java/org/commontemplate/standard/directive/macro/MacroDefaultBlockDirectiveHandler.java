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

	private String defaultBlockDirectiveSuffix = ".block";

	public void setDefaultBlockDirectiveSuffix(String defaultBlockDirectiveSuffix) {
		this.defaultBlockDirectiveSuffix = defaultBlockDirectiveSuffix;
	}

	public void doRender(Context context, String name,
			Object param, List innerElements) throws Exception {
		Assert.assertNotNull(name);
		if (defaultBlockDirectiveSuffix != null && name.endsWith(defaultBlockDirectiveSuffix))
			name = name.substring(0, name.length() - defaultBlockDirectiveSuffix.length());
		List macro = (List)context.getProperty(MacroDirectiveHandler.MACRO_TYPE, name);
		Assert.assertNotNull(macro, "MacroDefaultBlockDirectiveHandler.invaild.macro.name", new Object[]{name});
		Map variables = ParameterUtils.getParameters(param);
		context.pushLocalContext(variables);
		context.putProperty(InnerDirectiveHandler.INNER_BLOCK, innerElements);
		try {
			DirectiveUtils.renderAll(macro, context);
		} catch (ReturnException e) {
			// ignore
		} finally {
			context.popLocalContext();
		}
	}

}
