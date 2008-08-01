package org.commontemplate.standard.function.string;

import java.util.List;

import org.commontemplate.standard.function.FunctionHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.util.Assert;
import org.commontemplate.util.StringUtils;

public class StringReplaceFunctionHandler extends FunctionHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doFunction(Object bean, List args) throws Exception {
		String str = (String)bean;
		if (args != null && args.size() == 2) {
			Assert.assertTrue(args.get(0) instanceof String, "StringReplaceFunctionHandler.parameter.invalid");
			Assert.assertTrue(args.get(1) instanceof String, "StringReplaceFunctionHandler.parameter.invalid");
			String searchString = (String)args.get(0);
			String replacement = (String)args.get(1);
			return StringUtils.replace(str, searchString, replacement);
		} else {
			throw new UnhandleException("StringReplaceFunctionHandler.parameter.countInvalid");
		}
	}

}
