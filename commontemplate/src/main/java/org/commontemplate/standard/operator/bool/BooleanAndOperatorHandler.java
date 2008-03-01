package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔值"短路与"运算
 * 
 * @author liangfei0201@163.com
 *
 */
public class BooleanAndOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanAndOperatorHandler() {
		super(Object.class, LazyOperand.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		if (! TypeUtils.isTrue(leftOperand)) 
			return Boolean.FALSE;
		return Boolean.valueOf(TypeUtils.isTrue(((LazyOperand)rightOperand).evaluate()));
	}

}