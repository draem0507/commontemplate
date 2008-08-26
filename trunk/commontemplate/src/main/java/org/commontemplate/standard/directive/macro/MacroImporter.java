package org.commontemplate.standard.directive.macro;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.standard.filter.IgnoreOutputFilter;

public class MacroImporter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String namespaceSeparator;

	public void setNamespaceSeparator(String namespaceSeparator) {
		this.namespaceSeparator = namespaceSeparator;
	}

	public void importMacro(Context context, String namespace, String templateName) throws Exception {
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
