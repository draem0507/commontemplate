package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔"短路选择"逻辑三目操作符: "?:"<br/>
 * 如: isOk ? "yes" : "no"<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BooleanSelectOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanSelectOperatorHandler() {
		super(Object.class, LazyOperand.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		if (TypeUtils.isTrue(leftOperand))
			return new Selector(true, ((LazyOperand)rightOperand).evaluate());
		return new Selector(false, null);
	}

}