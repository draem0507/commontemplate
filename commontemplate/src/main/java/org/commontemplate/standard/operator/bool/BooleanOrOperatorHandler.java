package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔值"短路或"逻辑操作符: "||" 或 "or"<br/>
 * 如: $if{isX || isY} $if{isX or isY}<br/>
 * 返回第一个为真(不为null,不为空,不为0,不为flase)的对象<br/>
 * 如: ${user1 || user2 || user3}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BooleanOrOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanOrOperatorHandler() {
		super(Object.class, LazyOperand.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		if (TypeUtils.isTrue(leftOperand))
			return leftOperand;
		if (rightOperand == null)
			return null;
		return ((LazyOperand)rightOperand).evaluate();
	}

}