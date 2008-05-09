package org.commontemplate.standard.directive.include;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.standard.visit.ElementsVisitor;
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
			Assert.assertTrue(list.size() > 1, "include参数列表错误!");
			Assert.assertTrue(list.get(0) instanceof String, "include参数列表错误!");
			templateName = (String)list.get(0);
			if (list.size() == 2) {
				Object p2 = list.get(1);
				if (p2 instanceof String) {
					templateEncoding = (String)p2;
				} else if (p2 instanceof Entry || p2 instanceof Map) {
					variables = ParameterUtils.getParameters(p2);
				} else {
					Assert.fail("include参数列表错误!");
				}
			} else if (list.size() == 3) {
				Assert.assertTrue(list.get(1) instanceof String, "include参数列表错误!");
				Assert.assertTrue(list.get(2) instanceof Entry || list.get(2) instanceof Map, "include参数列表错误!");
				templateEncoding = (String)list.get(1);
				variables = ParameterUtils.getParameters(list.get(2));
			} else {
				Assert.fail("include参数列表错误!");
			}
		} else {
			Assert.fail("include参数列表错误!");
		}
		if (templateName == null || templateName.trim().length() == 0)
			throw new RuntimeException("include模板名称不能为空!");
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
				newContext.defineAllVariables(variables);
			if (zoneName != null && zoneName.length() > 0) {
				List elements = ElementsVisitor.findElements(template, "zone", zoneName);
				if (elements == null)
					throw new RuntimeException("在模板: " + templateName + " 中未找到zone: " + zoneName);
				DirectiveUtils.renderAll(elements, newContext);
			} else {
				template.render(newContext);
			}
		} finally {
			newContext.clear();
		}
	}

}