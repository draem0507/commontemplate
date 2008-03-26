package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 字符变量是否存在判断操作符: "?"<br/>
 * 如: ${? str}<br/>
 * TODO 待考虑是否需要些操作符<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringPresenceOperatorHandler() {
		super(String.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		String str = (String)operand;
		return Boolean.valueOf(str != null && str.trim().length() > 0);
	}

}