package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class MapRepeatOpreatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapRepeatOpreatorHandler() {
		super(Map.class, Integer.class, false, false, true); // 左右参数可交换
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map src;
		int repeat;
		if (leftOperand instanceof Map) {
			src = (Map)leftOperand;
			repeat = ((Integer)rightOperand).intValue();
		} else {
			src = (Map)rightOperand;
			repeat = ((Integer)leftOperand).intValue();
		}
		Map dest = null; // 新建Collection对象, 以保证不改变原有数据模型
		try {
			dest = (Map) src.getClass().newInstance();
			for (int i = 0; i < repeat; i ++) {
				dest.putAll(src); // 有些集合可能不支持addAll操作, 所以放在try内
			}
		} catch (Exception e) {
			dest = new HashMap(src.size() * repeat);
			for (int i = 0; i < repeat; i ++) {
				dest.putAll(src);
			}
		}
		return dest;
	}

}