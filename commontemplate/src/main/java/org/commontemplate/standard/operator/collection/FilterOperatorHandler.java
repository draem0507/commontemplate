package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 过滤条件项(默认名称)一元操作符: "=>"<br/>
 * 如：$filter{=> value.trim} ${users[=> item.name != "guest"]}<br/>
 * 与二元的"=>"功能相同, 只是采用默认名称．
 *
 * @see org.commontemplate.standard.operator.collection.FilterEntryOperatorHandler
 * @author liangfei0201@163.com
 *
 */
public class FilterOperatorHandler extends UnaryOperatorHandlerSupport {

	public FilterOperatorHandler() {
		super(LazyOperand.class);
	}

	private static final long serialVersionUID = 1L;

	public Object doEvaluate(Object operand) throws Exception {
		return new Filter(((LazyOperand)operand).getExpression());
	}

}