package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.operator.collection.Filter;

public class FilterDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		OutputFilter outputFilter = new ValueOutputFilter((Filter)param, getValueName());
		context.setOutputFilter(outputFilter);
		for (int i = 0, n = innerElements.size(); i < n; i ++) {
			Element directive = (Element)innerElements.get(i);
			directive.render(context);
		}
		context.removeOutputFilter();
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
