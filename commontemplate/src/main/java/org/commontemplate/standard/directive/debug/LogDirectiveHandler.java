package org.commontemplate.standard.directive.debug;

import java.util.Map.Entry;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.util.Assert;

public class LogDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			context.debug((String)param);
		} else if (param instanceof Entry) {
			Entry entry = (Entry)param;
			String level = (String)entry.getKey();
			String msg = (String)entry.getValue();
			if ("debug".equals(level)) {
				context.debug(msg);
			} else if ("info".equals(level)) {
				context.info(msg);
			} else if ("warn".equals(level)) {
				context.warn(msg);
			} else if ("error".equals(level)) {
				context.error(msg);
			} else {
				Assert.fail("错误的日志级别：" + level);
			}
		} else {
			Assert.fail("log指令参数列表错误！");
		}
	}

}
