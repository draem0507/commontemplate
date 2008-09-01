package org.commontemplate.standard.directive.output;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.i18n.MessageSource;
import org.commontemplate.standard.i18n.NoSuchMessageException;
import org.commontemplate.core.Context;

/**
 * 国际化信息输出指令.
 *
 * @author liangfei0201@163.com
 *
 */
public class I18nMessageDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param != null) {
			if (param instanceof List) {
				List list = (List)param;
				String key = list.get(0).toString();
				list.remove(0);
				try {
					context.output(messageSource.getMessage(context.getLocale(), key, list.toArray()));
				} catch (NoSuchMessageException e) {
					context.output("????" + key + "????");
				}
			} else {
				try {
					context.output(messageSource.getMessage(context.getLocale(), param.toString()));
				} catch (NoSuchMessageException e) {
					context.output("????" + param + "????");
				}
			}
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
