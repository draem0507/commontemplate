package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.operator.collection.Filter;

public class FilterAllDirectiveHandler implements BlockDirectiveHandler {

	private static final long serialVersionUID = 1L;
	
	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		context.setOutputFilter(new ValueOutputFilter((Filter)param, getValueName()));
		DirectiveUtils.renderAll(innerElements, context);
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

}
