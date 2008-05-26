package org.commontemplate.standard.directive.block;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.macro.MacroDirectiveHandler;
import org.commontemplate.util.Assert;

/**
 * 使用模板块作为宏.
 *
 * @author liangfei0201@163.com
 *
 */
public class UsingAsMacroDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof Entry) {
			Entry entry = (Entry)param;
			context.putProperty(MacroDirectiveHandler.MACRO_TYPE, (String)entry.getKey(), context.getProperty(BlockDefineDirectiveHandler.BLOCK_TYPE, (String)entry.getValue()));
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				context.putProperty(MacroDirectiveHandler.MACRO_TYPE, (String)entry.getKey(), context.getProperty(BlockDefineDirectiveHandler.BLOCK_TYPE, (String)entry.getValue()));
			}
		} else {
			Assert.fail("UsingAsMacroDirectiveHandler.parameter.error");
		}
	}

}