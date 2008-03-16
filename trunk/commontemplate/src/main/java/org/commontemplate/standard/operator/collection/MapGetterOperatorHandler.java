package org.commontemplate.standard.operator.collection;

import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * Map按Key索引取值操作符: "[]"<br/>
 * 如: ${map[key="value"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class MapGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapGetterOperatorHandler() {
		super(Map.class, Object.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return ((Map)leftOperand).get(rightOperand);
	}

}