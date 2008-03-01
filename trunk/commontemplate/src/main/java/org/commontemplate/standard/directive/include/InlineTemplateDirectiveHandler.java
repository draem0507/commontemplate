package org.commontemplate.standard.directive.include;

import java.util.List;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.visit.ElementsVisitor;
import org.commontemplate.util.Assert;

/**
 * 内嵌另一模板，被内嵌的模板共享当前Context，可直接取变量
 * 语法：
 * $inline{"xxx.ctl"}
 *
 * @author liangfei0201@163.com
 *
 */
public class InlineTemplateDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		String templateName = null;
		String templateEncoding = null;
		if (param instanceof String) {
			templateName = (String)param;
		} else if (param instanceof List) {
			List list = (List)param;
			Assert.assertTrue(list.size() == 2, "inline参数列表错误!");
			Assert.assertTrue(list.get(0) instanceof String, "inline参数列表错误!");
			Assert.assertTrue(list.get(1) instanceof String, "inline参数列表错误!");
			templateName = (String)list.get(0);
			templateEncoding = (String)list.get(1);
		} else {
			Assert.fail("inline参数列表错误!");
		}
		if (templateName == null || templateName.trim().length() == 0)
			throw new RuntimeException("inline模板名称不能为空!");
		String zoneName = null;
		int index = templateName.indexOf('#');
		if (index >= 0) {
			zoneName = templateName.substring(index + 1);
			templateName = templateName.substring(0, index);
		}
		if (context.containsTemplate(context.relateTemplateName(templateName))) // 循环内嵌检测
			throw new RuntimeException("内嵌(inline)模板时出现循环内嵌！请检查从模板 " + templateName + " 到模板 " + context.getCurrentTemplate() + " 之间的内嵌关系。");
		Template template = context.getTemplate(templateName, templateEncoding); // getTemplate的后验条件已保证不返回null
		if (zoneName != null && zoneName.length() > 0) {
			List elements = ElementsVisitor.findElements(template, "zone", zoneName);
			if (elements == null)
				throw new RuntimeException("在模板: " + templateName + " 中未找到zone: " + zoneName);
			DirectiveUtils.renderAll(elements, context);
		} else {
			template.render(context);
		}
	}

}