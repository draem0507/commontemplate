package org.commontemplate.standard.directive.output;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedOutputFilter;
import org.commontemplate.standard.i18n.MessageSource;
import org.commontemplate.standard.i18n.NoSuchMessageException;

public class I18nMessageStartDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param != null) {
			if (param instanceof List) {
				List list = (List)param;
				String key = list.get(0).toString();
				list.remove(0);
				try {
					context.output(messageSource.getMessage(context.getLocale(), key, list.toArray()));
				} catch (NoSuchMessageException e) {
					context.output(getInnerText(context, innerElements));
				}
			} else {
				try {
					context.output(messageSource.getMessage(context.getLocale(), param.toString()));
				} catch (NoSuchMessageException e) {
					context.output(getInnerText(context, innerElements));
				}
			}
		}
	}

	private String getInnerText(Context context, List elements) {
		BufferedOutputFilter bufferedFilter = new BufferedOutputFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(elements, context);
		context.removeOutputFilter();
		return bufferedFilter.getBuffered();
	}

	public boolean isExpressionRequired() {
		return true;
	}

}