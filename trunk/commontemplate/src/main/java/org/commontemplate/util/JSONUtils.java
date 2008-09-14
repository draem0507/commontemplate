package org.commontemplate.util;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.commontemplate.util.coder.JavaScript;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * JSON简化使用工具类
 *
 * @author liangfei0201@163.com
 * @author 陈志强
 */
public final class JSONUtils {

	// 如果两个对象互引用，则死循环递归，这里做最大递归限制
	private static final int MAX_RECURSION = 32;

	private JSONUtils() {}

	public static Object fromJson(String json) throws Exception {
		if (json == null)
			return null;
		if (json.trim().startsWith("["))
			return fromJsonToList(json);
		else
			return fromJsonToMap(json);
	}

	public static Map fromJsonToMap(String json) throws Exception {
		return jsonToMap(new JSONObject(json), 0);
	}

	public static List fromJsonToList(String json) throws Exception {
		return jsonToList(new JSONArray(json), 0);
	}

	private static Map jsonToMap(JSONObject json, int count) throws Exception {
		if (json == null || count > MAX_RECURSION)
			return null;
		Map map = new HashMap();
		for (Iterator iterator = json.keys(); iterator.hasNext();) {
			String key = (String)iterator.next();
			Object value = json.get(key);
			if (value instanceof JSONObject) {
				JSONObject obj = (JSONObject) value;
				map.put(key, jsonToMap(obj, count + 1));
			} else if (value instanceof JSONArray) {
				JSONArray arr = (JSONArray) value;
				map.put(key, jsonToList(arr, count + 1));
			} else {
				map.put(key, value);
			}
		}
		return map;
	}

	private static List jsonToList(JSONArray json, int count) throws Exception {
		if (json == null || count > MAX_RECURSION)
			return null;
		List list = new ArrayList();
		for (int i = 0, n = json.length(); i < n; i ++) {
			Object value = json.get(i);
			if (value instanceof JSONObject) {
				JSONObject obj = (JSONObject) value;
				list.add(jsonToMap(obj, count + 1));
			} else if (value instanceof JSONArray) {
				JSONArray arr = (JSONArray) value;
				list.add(jsonToList(arr, count + 1));
			} else {
				list.add(value);
			}
		}
		return list;
	}

	public static String toJson(Object bean) throws Exception {
		StringBuffer buf = new StringBuffer();
		appendObject(bean, buf, new Stack(), 0);
		return buf.toString();
	}

	// 判断是否循环引用
	public static boolean isCycleReferenced(Object object, Stack ref) {
		if (! ref.isEmpty()) {
			for (Iterator i = ref.iterator(); i.hasNext();) {
				if (i.next() == object)
					return true;
			}
		}
		return false;
	}

	private static void appendObject(Object object, StringBuffer buf, Stack ref, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		if (object == null) {
			buf.append("null");
		} else if ( object.getClass() == boolean.class
				|| object.getClass() == byte.class
				|| object.getClass() == short.class
				|| object.getClass() == int.class
				|| object.getClass() == long.class
				|| object.getClass() == float.class
				|| object.getClass() == double.class
				|| object.getClass() == Boolean.class
				|| object.getClass() == Byte.class
				|| object.getClass() == Short.class
				|| object.getClass() == Integer.class
				|| object.getClass() == Long.class
				|| object.getClass() == Float.class
				|| object.getClass() == Double.class) {
			buf.append(String.valueOf(object));
		} else if (object.getClass() == char.class
				|| object.getClass() == Character.class) {
			buf.append("\'" + String.valueOf(object) + "\'");
		} else if (object instanceof CharSequence) {
			buf.append("\"" + JavaScript.encode(object.toString()) + "\"");
		} else if (object instanceof Date) {
			buf.append("\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format((Date)object) + "\"");
		} else if (isCycleReferenced(object, ref)){
			buf.append("null"); // 如果循环引用，则用null代替
		} else {
			ref.push(object); // 压入引用
			if (object.getClass().isArray()) {
				appendArray(object, buf, ref, count + 1);
			} else if (object instanceof Collection) {
				appendCollection((Collection)object, buf, ref, count + 1);
			} else if (object instanceof Map) {
				appendMap((Map)object, buf, ref, count + 1);
			} else {
				appendMap(BeanUtils.getProperties(object), buf, ref, count + 1);
			}
			ref.pop(); // 弹出引用
		}
	}

	// 添加数组，包括基本类型数组
	private static void appendArray(Object array, StringBuffer buf, Stack ref, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		buf.append("[");
		boolean isFirst = true;
		for (int i = 0, n = Array.getLength(array); i < n; i ++) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(Array.get(array, i), buf, ref, count + 1);
		}
		buf.append("]");
	}

	// 添加集合
	private static void appendCollection(Collection collection, StringBuffer buf, Stack ref, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		buf.append("[");
		boolean isFirst = true;
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(iterator.next(), buf, ref, count + 1);
		}
		buf.append("]");
	}

	// 添加Map
	private static void appendMap(Map properties, StringBuffer buf, Stack ref, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		buf.append("{");
		boolean isFirst = true;
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			Map.Entry entry = (Map.Entry)iterator.next();
			buf.append(filterKey(entry.getKey()));
			buf.append(":");
			appendObject(entry.getValue(), buf, ref, count + 1);
		}
		buf.append("}");
	}

	// 过滤名称
	private static String filterKey(Object property) throws Exception {
		if (TypeUtils.isNamed(String.valueOf(property)))
			return String.valueOf(property);
		return "\"" + JavaScript.encode(property.toString()) + "\"";
	}

}
