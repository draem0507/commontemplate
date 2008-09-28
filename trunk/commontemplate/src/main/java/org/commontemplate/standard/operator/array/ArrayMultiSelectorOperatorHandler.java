package org.commontemplate.standard.operator.array;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;

public class ArrayMultiSelectorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayMultiSelectorOperatorHandler() {
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class}, new Class[]{Map.class});
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map map = (Map)rightOperand;
		for (int i = 0, n = Array.getLength(leftOperand); i < n; i ++) {
			Object obj = Array.get(leftOperand, i);
			if (match(obj, map))
				return obj;
		}
		return null;
	}

	private boolean match(Object obj, Map map) {
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String peoperty = (String)entry.getKey();
			Object value = entry.getValue();
			try {
				Object pro = BeanUtils.getProperty(obj, peoperty);
				if (! ((value == null && pro == null) || (value != null && value.equals(pro))))
					return false;
			} catch (Exception e) {
				// Ignore, continue next
			}
		}
		return true;
	}

}