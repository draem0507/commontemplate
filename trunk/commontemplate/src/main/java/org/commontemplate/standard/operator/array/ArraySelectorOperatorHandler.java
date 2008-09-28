package org.commontemplate.standard.operator.array;

import java.lang.reflect.Array;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;

/**
 * 数组内容选择操作符: "[:]"<br/>
 * 如: ${users[name:"xxx"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ArraySelectorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArraySelectorOperatorHandler() {
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class}, new Class[]{Entry.class});
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Entry entry = (Entry)rightOperand;
		String peoperty = (String)entry.getKey();
		Object value = entry.getValue();
		for (int i = 0, n = Array.getLength(leftOperand); i < n; i ++) {
			Object obj = Array.get(leftOperand, i);
			try {
				Object pro = BeanUtils.getProperty(obj, peoperty);
				if ((value == null && pro == null) ||
						(value != null && value.equals(pro)))
					return obj;
			} catch (Exception e) {
				// Ignore, continue next
			}
		}
		return null;
	}



}