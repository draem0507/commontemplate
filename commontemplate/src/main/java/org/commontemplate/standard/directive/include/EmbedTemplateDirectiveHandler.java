package org.commontemplate.standard.directive.include;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.visitor.BlockDirectiveVisitor;
import org.commontemplate.util.Assert;

/**
 * 内嵌另一模板，被内嵌的模板共享当前Context，可直接取变量 语法： $embed{"xxx.ctl"}
 *
 * @author liangfei0201@163.com
 *
 */
public class EmbedTemplateDirectiveHandler extends DirectiveHandlerSupport {

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
		if (param instanceof String) {
			templateName = (String) param;
		} else if (param instanceof List) {
			List list = (List) param; // assert(list != null);
			Assert.assertTrue(list.size() == 2,
					"EmbedTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
			Assert.assertTrue(list.get(0) instanceof String,
					"EmbedTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
			Assert.assertTrue(list.get(1) instanceof String,
					"EmbedTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
			templateName = (String) list.get(0);
			templateEncoding = (String) list.get(1);
		} else {
			Assert.fail("EmbedTemplateDirectiveHandler.parameter.error",
					new Object[] { param });
		}
		Assert.assertNotEmpty(templateName,
				"EmbedTemplateDirectiveHandler.template.name.required");
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
		// assert(template != null);
		templateName = template.getName();
		// 循环内嵌检测
		Assert.assertTrue(context.findTemplate(templateName) == null,
				"EmbedTemplateDirectiveHandler.cycle.embed", new Object[] {
						templateName, context.getCurrentTemplate() });
		if (zoneName != null && zoneName.length() > 0) {
			List elements = BlockDirectiveVisitor.findBlockDirectiveElements(
					template, "zone", zoneName, context);
			Assert.assertNotEmpty(elements,
					"EmbedTemplateDirectiveHandler.template.zone.not.found",
					new Object[] { templateName, zoneName });
			DirectiveUtils.renderAll(elements, context);
		} else {
			template.render(context);
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}