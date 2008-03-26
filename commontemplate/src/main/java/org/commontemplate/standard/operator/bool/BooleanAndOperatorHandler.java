package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔值"短路与"逻辑操作符: "&&" 或 "and"<br/>
 * 如: $if{isX && isY} $if{isX and isY}<br/>
 * 返回第一个不为真(为null,为空,为0,为flase)的对象, 如: <br/>
 * ${2 && 0 && 1} 输出 0<br/>
 * ${"ok" && ""} 输出 ""<br/>
 *
 * @see org.commontemplate.util.TypeUtils#isTrue(Object)
 * @author liangfei0201@163.com
 *
 */
public class BooleanAndOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanAndOperatorHandler() {
		super(Object.class, LazyOperand.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		if (! TypeUtils.isTrue(leftOperand))
			return leftOperand;
		if (rightOperand == null)
			return null;
		return ((LazyOperand)rightOperand).evaluate();
	}

}