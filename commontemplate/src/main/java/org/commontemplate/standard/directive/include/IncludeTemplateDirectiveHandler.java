package org.commontemplate.standard.directive.include;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.standard.visitor.BlockDirectiveVisitor;
import org.commontemplate.util.Assert;

/**
 * 包含另一模板的输出指令处理器.
 *
 * @author liangfei0201@163.com
 *
 */
public class IncludeTemplateDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	// 是否本地化查找模板, 如: xxx_zh_CN.ctl
	private boolean localizedLookup;

	public boolean isLocalizedLookup() {
		return localizedLookup;
	}

	public void setLocalizedLookup(boolean localizedLookup) {
		this.localizedLookup = localizedLookup;
	}

	public void doRender(Context context, String directiveName, Object param)
			throws Exception {
		String templateName = null;
		String templateEncoding = null;
		Map variables = null;
		if (param instanceof String) {
			templateName = (String) param;
		} else if (param instanceof List) {
			List list = (List) param;
			Assert.assertTrue(list.size() > 1,
					"IncludeTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
			Assert.assertTrue(list.get(0) instanceof String,
					"IncludeTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
			templateName = (String) list.get(0);
			if (list.size() == 2) {
				Object p2 = list.get(1);
				if (p2 instanceof String) {
					templateEncoding = (String) p2;
				} else if (p2 instanceof Entry || p2 instanceof Map) {
					variables = ParameterUtils.getParameters(p2);
				} else {
					Assert.fail(
							"IncludeTemplateDirectiveHandler.parameter.error",
							new Object[] { param });
				}
			} else if (list.size() == 3) {
				Assert.assertTrue(list.get(1) instanceof String,
						"IncludeTemplateDirectiveHandler.parameter.error",
						new Object[] { param });
				Assert.assertTrue(list.get(2) instanceof Entry
						|| list.get(2) instanceof Map,
						"IncludeTemplateDirectiveHandler.parameter.error",
						new Object[] { param });
				templateEncoding = (String) list.get(1);
				variables = ParameterUtils.getParameters(list.get(2));
			} else {
				Assert.fail("IncludeTemplateDirectiveHandler.parameter.error",
						new Object[] { param });
			}
		} else {
			Assert.fail("IncludeTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
		}
		Assert.assertNotEmpty(templateName,
				"IncludeTemplateDirectiveHandler.template.name.required");
		String zoneName = null;
		int index = templateName.indexOf('#');
		if (index >= 0) {
			zoneName = templateName.substring(index + 1);
			templateName = templateName.substring(0, index);
		}
		Template template;
		if (localizedLookup)
			template = context.getTemplateLoader().getTemplate(templateName, context.getLocale(),
					templateEncoding);
		else
			template = context.getTemplateLoader().getTemplate(templateName, templateEncoding);
		// assert(template != null); // getTemplate的后验条件已保证不返回null
		templateName = template.getName();
		// 在新的上下文执行
		Context newContext = context.createContext();
		try {
			if (variables != null && variables.size() > 0)
				newContext.putAllVariables(variables);
			if (zoneName != null && zoneName.length() > 0) {
				List elements = BlockDirectiveVisitor
						.findBlockDirectiveElements(template, "zone", zoneName,
								context);
				Assert
						.assertNotEmpty(
								elements,
								"IncludeTemplateDirectiveHandler.template.zone.not.found",
								new Object[] { templateName, zoneName });
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