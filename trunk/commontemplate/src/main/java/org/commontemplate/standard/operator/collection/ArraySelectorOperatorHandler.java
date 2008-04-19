package org.commontemplate.standard.operator.collection;

import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;

/**
 * 数组内容选择操作符: "[=]"<br/>
 * 如: ${users[name="xxx"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ArraySelectorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArraySelectorOperatorHandler() {
		super(Object[].class, Entry.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Object[] array = (Object[])leftOperand;
		Entry entry = (Entry)rightOperand;
		String peoperty = (String)entry.getKey();
		Object value = entry.getValue();
		for (int i = 0, n = array.length; i < n; i ++) {
			Object obj = array[i];
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