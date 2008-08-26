package org.commontemplate.standard.context;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.config.ContextInitializer;
import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.macro.MacroImporter;

/**
 * 自动导入宏
 *
 * @author liangfei0201@163.com
 *
 */
public class AutoImportInitializer implements ContextInitializer, Serializable {

	private static final long serialVersionUID = 1L;

	private MacroImporter macroImporter;

	public void setMacroImporter(MacroImporter macroImporter) {
		this.macroImporter = macroImporter;
	}

	private Map autoImports;

	public void setAutoImports(Map autoImports) {
		this.autoImports = autoImports;
	}

	public void initialize(Context context) {
		if (autoImports != null && autoImports.size() > 0) {
			for (Iterator iterator = autoImports.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				String namespace = (String)entry.getKey();
				if (namespace != null && namespace.length() == 0)
					namespace = null;
				String templateNames = (String)entry.getValue();
				if (templateNames != null) {
					String[] templates = templateNames.split("\\,");
					for (int i = 0, n = templates.length; i < n; i ++) {
						String template = templates[i];
						if (template != null) {
							try {
								macroImporter.importMacro(context, namespace, template);
							} catch (RuntimeException e) {
								throw e;
							} catch (Exception e) {
								throw new RuntimeException(e.getMessage(), e);
							}
						}
					}

				}
			}
		}
	}

}
