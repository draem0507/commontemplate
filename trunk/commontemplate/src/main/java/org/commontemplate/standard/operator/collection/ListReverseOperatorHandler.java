package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 列表反转一元操作符: "-"<br/>
 * 如: ${- list}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ListReverseOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ListReverseOperatorHandler() {
		super(List.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		List re = new ArrayList((List)operand);
		java.util.Collections.reverse(re);
		return re;
	}

}