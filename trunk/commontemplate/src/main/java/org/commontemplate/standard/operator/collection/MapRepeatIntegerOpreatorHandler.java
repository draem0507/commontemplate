package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class MapRepeatIntegerOpreatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapRepeatIntegerOpreatorHandler() {
		super(Integer.class, Map.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		int repeat = ((Integer)leftOperand).intValue();
		Map src = (Map)rightOperand;
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