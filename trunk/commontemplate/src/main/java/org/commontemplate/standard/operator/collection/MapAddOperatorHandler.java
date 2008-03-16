package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * Map数据相加操作符: "+"<br/>
 * 如: ${map1 + map2}<br/>
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

		/* 允许不同Map实现类相加
		if(! leftMap.getClass().getName().equals(rightMap.getClass().getName())) {
			Assert.fail("leftOperand and rightOperand is not same implement class!");
		}*/

		Map sumMap = null;// 新建Map对象, 以保证不改变原有数据模型
		try {
			sumMap = (Map) leftMap.getClass().newInstance();
		} catch (Exception e) { // 当左Map实现类没有无参构造子时, 试用右Map实现类
			try {
				sumMap = (Map) rightMap.getClass().newInstance();
			} catch (Exception e2) { // 默认采用HashMap
				sumMap = new HashMap(leftMap.size() + rightMap.size());
			}
		}
		sumMap.putAll(leftMap);
		sumMap.putAll(rightMap);
		return sumMap;
	}

}