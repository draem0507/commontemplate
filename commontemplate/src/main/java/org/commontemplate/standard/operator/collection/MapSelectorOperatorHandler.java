package org.commontemplate.standard.operator.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.MapEntry;

public class MapSelectorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapSelectorOperatorHandler() {
		super(Map.class, Entry.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map map = (Map)leftOperand;
		Entry entry = (Entry)rightOperand;
		String peoperty = (String)entry.getKey();
		Object value = entry.getValue();
		if ("key".equals(peoperty)) {
			Object obj = ((Map)leftOperand).get(value);
			return new MapEntry(value, obj);
		} else if ("value".equals(peoperty)) {
			for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry e = (Map.Entry)iterator.next();
				Object v = e.getValue();
				if ((value == null && v == null) 
						|| (value != null && value.equals(v)))
					return e;
			}
		} else {
			Assert.fail("java.util.Map只支持key和value两个属性！没有属性：" + peoperty);
		}
		return null;
	}

}