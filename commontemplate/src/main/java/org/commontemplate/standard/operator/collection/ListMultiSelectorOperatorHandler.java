package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.ClassUtils;

public class ListMultiSelectorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ListMultiSelectorOperatorHandler() {
		super(Collection.class, Map.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection collection = (Collection)leftOperand;
		Map map = (Map)rightOperand;
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
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
				Object pro = ClassUtils.getObjectProperty(obj, peoperty);
				if (! ((value == null && pro == null) || (value != null && value.equals(pro))))
					return false;
			} catch (Exception e) {
				// Ignore, continue next
			}
		}
		return true;
	}
	
}