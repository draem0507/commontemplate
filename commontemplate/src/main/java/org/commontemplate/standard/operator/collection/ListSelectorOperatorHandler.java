package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;

/**
 * 列表属性选择操作符: "[]"<br/>
 * 如: ${users[name="james"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ListSelectorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ListSelectorOperatorHandler() {
		super(Collection.class, Entry.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection collection = (Collection)leftOperand;
		Entry entry = (Entry)rightOperand;
		String peoperty = (String)entry.getKey();
		Object value = entry.getValue();
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
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