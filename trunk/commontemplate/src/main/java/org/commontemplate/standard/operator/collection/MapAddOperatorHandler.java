package org.commontemplate.standard.operator.collection;

import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.Assert;

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
		
		if(!leftMap.getClass().getName().equals(rightMap.getClass().getName())) {
			Assert.fail("leftOperand and rightOperand is not same Implement Class!");
		}
		Class clazz = leftMap.getClass();
		Map sum = (Map) clazz.newInstance();
		//Map sum = new HashMap(leftMap.size() + rightMap.size()); // 新建Map对象, 以保证不改变原有数据模型
		sum.putAll(leftMap);
		sum.putAll(rightMap);
		return sum;
	}

}