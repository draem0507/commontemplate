package org.commontemplate.standard.operator.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.MapEntry;

/**
 * Map条件选择操作符: "[]"<br/>
 * 如: ${map[key:"x"].value} ${map[value:"x"].key}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
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
			Assert.fail("MapSelectorOperatorHandler.map.property.no.such", new Object[]{peoperty});
		}
		return null;
	}

}