package org.commontemplate.standard.directive.output;

import java.util.List;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.NoSuchMessageException;

/**
 * 国际化信息输出指令.
 * 
 * @author liangfei0201@163.com
 *
 */
public class I18nMessageDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param != null) {
			if (param instanceof List) {
				List list = (List)param;
				String key = list.get(0).toString();
				list.remove(0);
				try {
					context.output(context.getMessage(key, list.toArray()));
				} catch (NoSuchMessageException e) {
					context.output("????" + key + "????");
				}
			} else {
				try {
					context.output(context.getMessage(param.toString()));
				} catch (NoSuchMessageException e) {
					context.output("????" + param + "????");
				}
			}
		}
	}

}
