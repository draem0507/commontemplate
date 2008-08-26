package org.commontemplate.standard.directive.macro;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.util.Assert;

/**
 * 导入模板中的所有宏.
 *
 * @author liangfei0201@163.com
 *
 */
public class ImportAsMacroDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private MacroImporter macroImporter;

	public void setMacroImporter(MacroImporter macroImporter) {
		this.macroImporter = macroImporter;
	}

	protected void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			String str = (String)param;
			macroImporter.importMacro(context, null, str);
		} else if (param instanceof Entry) {
			Entry entry = (Entry)param;
			macroImporter.importMacro(context, entry.getKey().toString(), (String)entry.getValue());
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				macroImporter.importMacro(context, entry.getKey().toString(), (String)entry.getValue());
			}
		} else {
			Assert.fail("ImportAsMacroDirectiveHandler.parameter.error");
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}