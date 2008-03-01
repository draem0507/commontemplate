package org.commontemplate.standard.directive;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class ParameterUtils {
	
	private ParameterUtils () {}
	
	public static Map getParameters(Object param) {
		Map model = new HashMap();
		if (param instanceof Entry) {
			Entry entry = (Entry)param;
			model.put(entry.getKey().toString(), entry.getValue());
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				model.put(String.valueOf(entry.getKey()), entry.getValue());
			}
		}
		return model;
	}

}
