package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedFilter;
import org.commontemplate.standard.operator.collection.Filter;

public class FilterDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String text = bufferedFilter.getBuffered();
		String content = new ValueOutputFilter((Filter)param, getValueName()).filter(text);
		context.output(content);
	}

	private String valueName;

	private static final String DEFAULT_VALUE_NAME = "value";

	public final String getValueName() {
		if (valueName == null || valueName.length() == 0)
			return DEFAULT_VALUE_NAME;
		return valueName;
	}

	public final void setValueName(String valueName) {
		this.valueName = valueName;
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}
