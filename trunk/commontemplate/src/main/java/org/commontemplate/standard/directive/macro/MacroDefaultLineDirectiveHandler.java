package org.commontemplate.standard.directive.macro;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
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
		Map model = ParameterUtils.getParameters(param);
		List macro = (List)context.getProperty(MacroDirectiveHandler.MACRO_TYPE, name);
		Assert.assertNotNull(macro, "MacroDefaultLineDirectiveHandler.invaild.macro.name", new Object[]{name});
		Context newContext = context.createContext();
		if (model.size() > 0)
			newContext.pushLocalContext(model);
		try {
			DirectiveUtils.renderAll(macro, newContext);
		} catch (ReturnException e) {
			// ignore
		} finally {
			newContext.clear();
		}
	}

}