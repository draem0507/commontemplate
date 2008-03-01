package org.commontemplate.standard.directive.macro;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.visit.ElementsVisitor;

/**
 * 导入模板作为宏.
 *
 * @author liangfei0201@163.com
 *
 */
public class ImportAsMacroDirectiveHandler implements LineDirectiveHandler {

	// TODO 宏全导入: $import{"xxx.ctl#*"}, 以及导入前缀设置: $import{prefix: "xxx.ctl#*"}

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof Entry) {
			Entry entry = (Entry)param;
			importMacro(context, entry.getKey().toString(), (String)entry.getValue());
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				importMacro(context, entry.getKey().toString(), (String)entry.getValue());
			}
		}
	}

	private void importMacro(Context context, String macroName, String templateName) throws Exception {
		String zoneName = null;
		int index = templateName.indexOf('#');
		if (index >= 0) {
			zoneName = templateName.substring(index + 1);
			templateName = templateName.substring(0, index);
		}
		Template template = context.getTemplate(templateName);
		List elements = null;
		if (zoneName != null && zoneName.length() > 0) {
			elements = ElementsVisitor.findElements(template, "macro", zoneName);
		} else {
			elements = template.getElements();
		}
		context.putObject(MacroDirectiveHandler.MACRO_TYPE, macroName, elements);
	}

}