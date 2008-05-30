package org.commontemplate.standard.function.string;

import java.util.List;

import org.commontemplate.standard.function.FunctionHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.util.Assert;

public class StringAbbreviateFunctionHandler extends FunctionHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doFunction(Object bean, List args) throws Exception {
		String str = (String)bean;
		// TODO 待参考apache-commons StringUtils.abbreviate()实现
		if (args == null || args.size() == 0) {
			return "";
		} if (args.size() == 1) {
			Assert.assertTrue(args.get(0) instanceof Number, "String.abbreviate()参数必需为数字!"); // TODO 未国际化
			int len = ((Number)args.get(0)).intValue();
			if (len <= 0)
				return "";
			if (len < str.length())
				return str.substring(0, len);
			return str;
		} else if (args.size() == 2) {
			Assert.assertTrue(args.get(0) instanceof Number, "String.abbreviate()参数必需为数字!"); // TODO 未国际化
			Assert.assertTrue(args.get(1) instanceof Number, "String.abbreviate()参数必需为数字!"); // TODO 未国际化
			return str;
		} else {
			throw new UnhandleException("String.abbreviate()不支持两个以上参数!"); // TODO 未国际化
		}
	}

}
