package org.commontemplate.standard.function.string;

import java.util.List;

import org.commontemplate.standard.function.FunctionHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.util.Assert;
import org.commontemplate.util.StringUtils;

public class StringAbbreviateFunctionHandler extends FunctionHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doFunction(Object bean, List args) throws Exception {
		String str = (String)bean;
		if (args == null || args.size() == 0) {
			return "";
		} if (args.size() == 1) {
			Assert.assertTrue(args.get(0) instanceof Number, "StringAbbreviateFunctionHandler.parameter.invalid");
			int len = ((Number)args.get(0)).intValue();
			if (len <= 3)
				len = 4;
			return StringUtils.abbreviate(str, len);
		} else if (args.size() == 2) {
			Assert.assertTrue(args.get(0) instanceof Number, "StringAbbreviateFunctionHandler.parameter.invalid");
			Assert.assertTrue(args.get(1) instanceof Number, "StringAbbreviateFunctionHandler.parameter.invalid");
			int offset = ((Number)args.get(0)).intValue();
			int len = ((Number)args.get(1)).intValue();
			if (len <= 3)
				len = 4;
			return StringUtils.abbreviate(str, offset, len);
		} else {
			throw new UnhandleException("StringAbbreviateFunctionHandler.parameter.countInvalid");
		}
	}

}
