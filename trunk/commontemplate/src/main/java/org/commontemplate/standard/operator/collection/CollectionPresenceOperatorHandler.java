package org.commontemplate.standard.operator.collection;

import java.util.Collection;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 集合变量是否有值判断操作符: "?"<br/>
 * 如：$if{?obj}<br/>
 * TODO 此操作符是否需要待考虑<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionPresenceOperatorHandler() {
		super(Collection.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Collection c = (Collection)operand;
		return Boolean.valueOf(c != null && c.size() > 0);
	}

}