package org.commontemplate.standard.function.string;

import java.util.List;

import org.commontemplate.standard.function.FunctionHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.util.Assert;
import org.commontemplate.util.StringUtils;

public class StringRightPadFunctionHandler extends FunctionHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doFunction(Object bean, List args) throws Exception {
		String str = (String)bean;
		if (args == null || args.size() == 0) {
			throw new UnhandleException("StringRightPadFunctionHandler.parameter.required");
		} if (args.size() == 1) {
			Assert.assertTrue(args.get(0) instanceof Number, "StringRightPadFunctionHandler.parameter.invalid");
			int len = ((Number)args.get(0)).intValue();
			return StringUtils.rightPad(str, len);
		} else if (args.size() == 2) {
			Assert.assertTrue(args.get(0) instanceof Number, "StringRightPadFunctionHandler.parameter.invalid");
			Assert.assertTrue(args.get(1) instanceof String, "StringRightPadFunctionHandler.parameter.invalid");
			int len = ((Number)args.get(0)).intValue();
			String rep = (String)args.get(0);
			return StringUtils.rightPad(str, len, rep);
		} else {
			throw new UnhandleException("StringRightPadFunctionHandler.parameter.countInvalid");
		}
	}

}
