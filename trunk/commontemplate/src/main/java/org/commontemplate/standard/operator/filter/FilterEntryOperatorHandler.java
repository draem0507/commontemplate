package org.commontemplate.standard.operator.filter;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 过滤条件项操作符: "=>"<br/>
 * 如：$filter{v => v.trim} ${users[u => u.name != "guest"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class FilterEntryOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public FilterEntryOperatorHandler() {
		super(Object.class, Object.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Filter(String.valueOf(leftOperand), ((LazyOperand)rightOperand).getExpression());
	}
}