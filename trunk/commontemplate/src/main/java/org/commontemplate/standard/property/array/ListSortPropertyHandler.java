package org.commontemplate.standard.property.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ListSortPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		List list = (List)bean;
		List result = new ArrayList(list.size());
		result.addAll(list); // 不修改原有List
		Collections.sort(result);
		return result;
	}

}
