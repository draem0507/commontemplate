package org.commontemplate.standard.operator.map;

import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;

/**
 * Map按Key索引取值操作符: "[]"或"."<br/>
 * 如: ${map["key"]} 或 ${map.key}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class MapPropertyGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapPropertyGetterOperatorHandler() {
		super(Map.class, Object.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map map = (Map)leftOperand;
		if (! map.containsKey(rightOperand))
			throw new UnhandleException();
		return map.get(rightOperand);
	}

}