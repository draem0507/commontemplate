package org.commontemplate.util.json;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.TypeUtils;
import org.commontemplate.util.coder.JavaScript;

/**
 * JSON简化使用工具类
 *
 * @author liangfei0201@163.com
 */
public final class JSONUtils {

	// 如果两个对象互引用，则死循环递归，这里做最大递归限制
	private static final int MAX_RECURSION = 16;

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
		appendObject(bean, buf, new LinkedList(), 0);
		return buf.toString();
	}

	private static void appendObject(Object bean, StringBuffer buf, List beans, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		if (bean == null) {
			buf.append("null");
		} else if (bean.getClass().isPrimitive()
				|| bean.getClass() == Boolean.class
				|| bean.getClass() == Byte.class
				|| bean.getClass() == Short.class
				|| bean.getClass() == Integer.class
				|| bean.getClass() == Long.class
				|| bean.getClass() == Float.class
				|| bean.getClass() == Double.class
				|| bean.getClass() == Character.class
				|| bean.getClass() == String.class) {
			buf.append(filterValue(bean));
		} else if (bean instanceof Date) {
			buf.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format((Date)bean));
		} else if (isCycle(bean, beans)){
			buf.append("null"); // 如果循环引用，则用null代替
		} else if (bean.getClass().isArray()) {
			appendArray(bean, buf, beans, count + 1);
		} else if (bean instanceof Collection) {
			appendCollection((Collection)bean, buf, beans, count + 1);
		} else if (bean instanceof Map) {
			appendMap((Map)bean, buf, beans, count + 1);
		} else {
			appendMap(BeanUtils.getProperties(bean), buf, beans, count + 1);
		}
	}

	// 添加数组，包括基本类型数组
	private static void appendArray(Object array, StringBuffer buf, List beans, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		buf.append("[");
		boolean isFirst = true;
		for (int i = 0, n = Array.getLength(array); i < n; i ++) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(Array.get(array, i), buf, beans, count + 1);
		}
		buf.append("]");
	}

	// 添加集合
	private static void appendCollection(Collection collection, StringBuffer buf, List beans, int count) throws Exception {
		if (count > MAX_RECURSION)
			return;
		buf.append("[");
		boolean isFirst = true;
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(iterator.next(), buf, beans, count + 1);
		}
		buf.append("]");
	}

	// 添加Map
	private static void appendMap(Map properties, StringBuffer buf, List beans, int count) throws Exception {
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
			buf.append(filterName(entry.getKey()));
			buf.append(":");
			appendObject(entry.getValue(), buf, beans, count + 1);
		}
		buf.append("}");
	}

	private static boolean isCycle(Object bean, List beans) {
		for (Iterator i = beans.iterator(); i.hasNext();) {
			Object obj = i.next();
			if (obj == bean)
				return true;
		}
		return false;
	}

	// 过滤名称
	private static String filterName(Object property) throws Exception {
		if (TypeUtils.isNamed(String.valueOf(property)))
			return String.valueOf(property);
		return "\"" + JavaScript.encode(property.toString()) + "\"";
	}

	// 过滤值
	private static String filterValue(Object property) throws Exception {
		if (property instanceof CharSequence)
			return "\"" + JavaScript.encode(property.toString()) + "\"";
		return String.valueOf(property);
	}

}
