package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedOutputFilter;
import org.commontemplate.standard.operator.filter.Filter;

public class BufferDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		BufferedOutputFilter bufferedFilter = new BufferedOutputFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String buf = bufferedFilter.getBuffered();
		if (param != null) {
			String txt = new ValueOutputFilter((Filter)param, getValueName()).filter(buf);
			context.output(txt);
		} else {
			context.output(buf);
		}
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
