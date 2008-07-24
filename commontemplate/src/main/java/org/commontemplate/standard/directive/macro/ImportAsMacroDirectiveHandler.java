package org.commontemplate.standard.directive.macro;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Template;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.visit.BlockDirectiveVisitor;
import org.commontemplate.standard.visit.BlockDirectivesVisitor;

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

	private static final String DEFAULT_MACRO_DIRECTIVE_NAME = "macro";

	private String macroDirectiveName;

	public void setMacroDirectiveName(String macroDirectiveName) {
		this.macroDirectiveName = macroDirectiveName;
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
		}
	}

	private void importMacro(Context context, String namespace, String templateName) throws Exception {
		if (namespace != null && namespaceSeparator != null)
			namespace = namespace + namespaceSeparator;
		String zoneName = null;
		int index = templateName.indexOf('#');
		if (index >= 0) {
			zoneName = templateName.substring(index + 1);
			templateName = templateName.substring(0, index);
		}
		Template template = context.getTemplate(templateName);
		if (zoneName != null && zoneName.length() > 0) { // 导入指定的宏
			List elements = BlockDirectiveVisitor.findInnerElements(template, "macro", zoneName);
			String macroName = zoneName;
			if (namespace != null)
				macroName = namespace + macroName;
			context.putProperty(MacroDirectiveHandler.MACRO_TYPE, macroName, new Macro(elements, macroName, namespace));
		} else { // 导入所有宏
			List blockDirectives = BlockDirectivesVisitor.findBlockDirectives(template, (macroDirectiveName == null ? DEFAULT_MACRO_DIRECTIVE_NAME : macroDirectiveName));
			for (Iterator iterator = blockDirectives.iterator(); iterator.hasNext();) {
				BlockDirective blockDirective = (BlockDirective) iterator.next();
				Expression expression = blockDirective.getExpression();
				if (expression != null) {
					Object obj = expression.evaluate(context);
					if (obj != null) {
						String macroName = String.valueOf(obj);
						if (namespace != null)
							macroName = namespace + macroName;
						context.putProperty(MacroDirectiveHandler.MACRO_TYPE, macroName, new Macro(blockDirective.getElements(), macroName, namespace));
					}
				}
			}
		}
	}

}