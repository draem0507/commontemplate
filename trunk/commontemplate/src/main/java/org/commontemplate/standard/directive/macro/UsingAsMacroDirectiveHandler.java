package org.commontemplate.standard.directive.macro;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.visit.BlockDirectiveVisitor;
import org.commontemplate.util.UrlUtils;

/**
 * 使用模板作为宏.
 *
 * @author liangfei0201@163.com
 *
 */
public class UsingAsMacroDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MACRO_DIRECTIVE_NAME = "macro";

	private String macroDirectiveName;

	public void setMacroDirectiveName(String macroDirectiveName) {
		this.macroDirectiveName = macroDirectiveName;
	}

	protected void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			usingMacro(context, null, (String)param);
		} else if (param instanceof Entry) {
			Entry entry = (Entry)param;
			usingMacro(context, entry.getKey().toString(), (String)entry.getValue());
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				usingMacro(context, entry.getKey().toString(), (String)entry.getValue());
			}
		}
	}

	private void usingMacro(Context context, String macroName, String templateName) throws Exception {
		String zoneName = null;
		int index = templateName.indexOf('#');
		if (index >= 0) {
			zoneName = templateName.substring(index + 1);
			templateName = templateName.substring(0, index);
		}
		Template template = context.getTemplate(templateName);
		List elements = null;
		if (zoneName != null && zoneName.length() > 0) {
			elements = BlockDirectiveVisitor.findInnerElements(template, (macroDirectiveName == null ? DEFAULT_MACRO_DIRECTIVE_NAME : macroDirectiveName), zoneName, context);
			if (macroName == null)
				macroName = zoneName;
		} else {
			elements = template.getElements();
			if (macroName == null)
				macroName = UrlUtils.getSimpleName(templateName);
		}
		context.putProperty(MacroDirectiveHandler.MACRO_TYPE, macroName, new Macro(elements, macroName));
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}