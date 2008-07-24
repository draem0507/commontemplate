package org.commontemplate.standard.directive.macro;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.StandardDirectiveHandlerProvider;
import org.commontemplate.util.Assert;

public class MacroDefaultBlockDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private String defaultBlockDirectiveSuffix = StandardDirectiveHandlerProvider.DEFAULT_BLOCK_DIRECTIVE_SUFFIX;

	public void setDefaultBlockDirectiveSuffix(String defaultBlockDirectiveSuffix) {
		this.defaultBlockDirectiveSuffix = defaultBlockDirectiveSuffix;
	}

	public void doRender(Context context, String name,
			Object param, List innerElements) throws Exception {
		Assert.assertNotNull(name);
		if (defaultBlockDirectiveSuffix != null && name.endsWith(defaultBlockDirectiveSuffix))
			name = name.substring(0, name.length() - defaultBlockDirectiveSuffix.length());
		String namespace = (String)context.getProperty(Macro.NAMESPACE_TYPE);
		if (namespace != null)
			name = namespace + name;
		Macro macro = (Macro)context.getProperty(MacroDirectiveHandler.MACRO_TYPE, name);
		Assert.assertNotNull(macro, "MacroDefaultBlockDirectiveHandler.invaild.macro.name", new Object[]{name});
		macro.render(context, param, innerElements);
	}

}
