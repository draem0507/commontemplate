package org.commontemplate.standard.operator.collection;

import org.commontemplate.util.TypeUtils;

/**
 * 集合中不包含项匹配操作符: "!~"<br/>
 * 如: $if{"a" !~ {"a", "b", "c"}}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionNotContainOperatorHandler extends CollectionContainOperatorHandler {

	private static final long serialVersionUID = 1L;

	public CollectionNotContainOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(super.doEvaluate(leftOperand, rightOperand)));
	}

}