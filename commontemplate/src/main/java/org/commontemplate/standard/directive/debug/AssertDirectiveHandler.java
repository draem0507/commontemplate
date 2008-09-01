package org.commontemplate.standard.directive.debug;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.TypeUtils;

public class AssertDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof List) {
			List list = (List)param;
			Assert.assertTrue(list.size() == 2, "AssertDirectiveHandler.parameter.error");
			boolean isOK = TypeUtils.isTrue(list.get(0));
			if (! isOK)
				throw new IllegalStateException(String.valueOf(list.get(1)));
		} else {
			boolean isOK = TypeUtils.isTrue(param);
			if (! isOK)
				throw new IllegalStateException("AssertDirectiveHandler.assert.error");
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
