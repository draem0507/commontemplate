package org.commontemplate.standard.directive.macro;

import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.util.Assert;

/**
 * 动态指令处理器
 *
 * @author liangfei0201@163.com
 *
 */
public class MacroDefaultLineDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String name, Object param) throws Exception {
		String namespace = (String)context.getProperty(Macro.NAMESPACE_TYPE);
		if (namespace != null)
			name = namespace + name;
		Macro macro = (Macro)context.getProperty(MacroDirectiveHandler.MACRO_TYPE, name);
		Assert.assertNotNull(macro, "MacroDefaultLineDirectiveHandler.invaild.macro.name", new Object[]{name});
		Map variables = ParameterUtils.getParameters(param);
		macro.render(context, variables, null);
	}

}