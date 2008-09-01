package org.commontemplate.standard.directive.include;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.standard.visit.BlockDirectiveVisitor;
import org.commontemplate.util.Assert;

/**
 * 包含另一模板的输出指令处理器.
 *
 * @author liangfei0201@163.com
 *
 */
public class IncludeTemplateDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		String templateName = null;
		String templateEncoding = null;
		Map variables = null;
		if (param instanceof String) {
			templateName = (String)param;
		} else if (param instanceof List) {
			List list = (List)param;
			Assert.assertTrue(list.size() > 1, "IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
			Assert.assertTrue(list.get(0) instanceof String, "IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
			templateName = (String)list.get(0);
			if (list.size() == 2) {
				Object p2 = list.get(1);
				if (p2 instanceof String) {
					templateEncoding = (String)p2;
				} else if (p2 instanceof Entry || p2 instanceof Map) {
					variables = ParameterUtils.getParameters(p2);
				} else {
					Assert.fail("IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
				}
			} else if (list.size() == 3) {
				Assert.assertTrue(list.get(1) instanceof String, "IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
				Assert.assertTrue(list.get(2) instanceof Entry || list.get(2) instanceof Map, "IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
				templateEncoding = (String)list.get(1);
				variables = ParameterUtils.getParameters(list.get(2));
			} else {
				Assert.fail("IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
			}
		} else {
			Assert.fail("IncludeTemplateDirectiveHandler.parameter.error", new Object[]{param});
		}
		Assert.assertNotEmpty(templateName, "IncludeTemplateDirectiveHandler.template.name.required");
		String zoneName = null;
		int index = templateName.indexOf('#');
		if (index >= 0) {
			zoneName = templateName.substring(index + 1);
			templateName = templateName.substring(0, index);
		}
		Template template = context.getTemplate(templateName, templateEncoding); // getTemplate的后验条件已保证不返回null
		// 在新的上下文执行
		Context newContext = context.createContext();
		try {
			if (variables != null && variables.size() > 0)
				newContext.putAllVariables(variables);
			if (zoneName != null && zoneName.length() > 0) {
				List elements = BlockDirectiveVisitor.findInnerElements(template, "zone", zoneName, context);
				Assert.assertNotEmpty(elements, "IncludeTemplateDirectiveHandler.template.zone.not.found", new Object[]{templateName, zoneName});
				DirectiveUtils.renderAll(elements, newContext);
			} else {
				template.render(newContext);
			}
		} finally {
			newContext.clear();
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}