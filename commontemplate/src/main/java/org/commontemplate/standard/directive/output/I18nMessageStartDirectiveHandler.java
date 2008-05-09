package org.commontemplate.standard.directive.output;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.NoSuchMessageException;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.filter.BufferedFilter;

public class I18nMessageStartDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param != null) {
			if (param instanceof List) {
				List list = (List)param;
				String key = list.get(0).toString();
				list.remove(0);
				try {
					context.output(context.getMessage(key, list.toArray()));
				} catch (NoSuchMessageException e) {
					context.output(getInnerText(context, innerElements));
				}
			} else {
				try {
					context.output(context.getMessage(param.toString()));
				} catch (NoSuchMessageException e) {
					context.output(getInnerText(context, innerElements));
				}
			}
		}
	}
	
	private String getInnerText(Context context, List elements) {
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(elements, context);
		context.removeOutputFilter();
		return bufferedFilter.getBuffered();
	}

}