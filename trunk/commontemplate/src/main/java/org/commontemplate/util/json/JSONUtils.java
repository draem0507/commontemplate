package org.commontemplate.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class JSONUtils {

	private JSONUtils() {}

	public static Map jsonToMap(JSONObject json) throws JSONException {
		if (json == null)
			return null;
		Map map = new HashMap();
		for (Iterator iterator = json.keys(); iterator.hasNext();) {
			String key = (String)iterator.next();
			Object value = json.get(key);
			if (value instanceof JSONObject) {
				JSONObject obj = (JSONObject) value;
				map.put(key, jsonToMap(obj));
			} else if (value instanceof JSONArray) {
				JSONArray arr = (JSONArray) value;
				map.put(key, jsonToList(arr));
			} else {
				map.put(key, value);
			}
		}
		return map;
	}

	public static List jsonToList(JSONArray json) throws JSONException {
		if (json == null)
			return null;
		List list = new ArrayList();
		for (int i = 0, n = json.length(); i < n; i ++) {
			Object value = json.get(i);
			if (value instanceof JSONObject) {
				JSONObject obj = (JSONObject) value;
				list.add(jsonToMap(obj));
			} else if (value instanceof JSONArray) {
				JSONArray arr = (JSONArray) value;
				list.add(jsonToList(arr));
			} else {
				list.add(value);
			}
		}
		return list;
	}

}
