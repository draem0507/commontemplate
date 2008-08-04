package org.commontemplate.standard.directive.macro;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.filter.IgnoreOutputFilter;
import org.commontemplate.util.Assert;

/**
 * 导入模板中的所有宏.
 *
 * @author liangfei0201@163.com
 *
 */
public class ImportAsMacroDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private String namespaceSeparator;

	public void setNamespaceSeparator(String namespaceSeparator) {
		this.namespaceSeparator = namespaceSeparator;
	}

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			String str = (String)param;
			importMacro(context, null, str);
		} else if (param instanceof Entry) {
			Entry entry = (Entry)param;
			importMacro(context, entry.getKey().toString(), (String)entry.getValue());
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				importMacro(context, entry.getKey().toString(), (String)entry.getValue());
			}
		} else {
			Assert.fail("ImportAsMacroDirectiveHandler.parameter.error");
		}
	}

	private void importMacro(Context context, String namespace, String templateName) throws Exception {
		if (namespace != null && namespaceSeparator != null)
			namespace = namespace + namespaceSeparator;
		Template template = context.getTemplate(templateName);
		Context newContext = context.createContext();
		try {
			newContext.setOutputFilter(IgnoreOutputFilter.getInstance());
			template.render(newContext);
			newContext.removeOutputFilter();
			Map macros = newContext.getProperties(MacroDirectiveHandler.MACRO_TYPE);
			for (Iterator iterator = macros.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String macroName = (String)entry.getKey();
				Macro macro = (Macro)entry.getValue();
				if (namespace != null) {
					macroName = namespace + macroName;
					macro = new Macro(macro, namespace);
				}
				context.putProperty(MacroDirectiveHandler.MACRO_TYPE, macroName, macro);
			}
		} finally {
			newContext.clear();
		}
	}

}