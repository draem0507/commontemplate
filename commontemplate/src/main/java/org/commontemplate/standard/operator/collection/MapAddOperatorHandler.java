package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 两个Map的数据相加
 * 
 * @author liangfei0201@163.com
 *
 */
public class MapAddOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapAddOperatorHandler() {
		super(Map.class, Map.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map leftMap = (Map)leftOperand;
		Map rightMap = (Map)rightOperand;
		Map sum = new HashMap(leftMap.size() + rightMap.size()); // 新建Map对象, 以保证不改变原有数据模型
		sum.putAll(leftMap);
		sum.putAll(rightMap);
		return leftOperand;
	}

}