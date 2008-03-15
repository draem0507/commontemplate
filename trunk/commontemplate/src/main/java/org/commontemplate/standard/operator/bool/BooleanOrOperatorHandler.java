package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔值"短路或"逻辑操作符: "||" 或 "or"<br/>
 * 如: $if{isX || isY} $if{isX or isY}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BooleanOrOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanOrOperatorHandler() {
		super(Object.class, LazyOperand.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		if (TypeUtils.isTrue(leftOperand))
			return leftOperand;
		return ((LazyOperand)rightOperand).evaluate();
	}

}