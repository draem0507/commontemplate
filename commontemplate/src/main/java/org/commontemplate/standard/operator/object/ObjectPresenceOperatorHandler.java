package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 对象是否存在判断一元操作符: "?"<br/>
 * 如: $if{? obj}<br/>
 * TODO 注: 此操作符待考虑是否需要<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ObjectPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ObjectPresenceOperatorHandler() {
		super(Object.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return Boolean.valueOf(operand != null);
	}

}