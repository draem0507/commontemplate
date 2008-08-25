package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.OutputConverter;
import org.commontemplate.core.Text;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.operator.collection.Filter;

public class ConvertDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		OutputConverter outputConverter = new ValueOutputConverter((Filter)param, getValueName());
		for (int i = 0, n = innerElements.size(); i < n; i ++) {
			Element directive = (Element)innerElements.get(i);
			if (directive instanceof Text) {
				directive.render(context);
			} else {
				context.setOutputConverter(outputConverter);
				directive.render(context);
				context.removeOutputConverter();
			}
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

	protected boolean isExpressionRequired() {
		return true;
	}

}
