package org.commontemplate.standard.directive.taglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

class TagUtils {

	private TagUtils(){}

	static void initTag(Object tag, Map attributes) throws SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, JspException {
		for (Iterator iterator = attributes.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry attribute = (Map.Entry)iterator.next();
			String name = (String)attribute.getKey();
			Object value = attribute.getValue();
			String setterName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
			Method[] methods = tag.getClass().getMethods();
			boolean hasSetter = false;
			for (int i = 0, n = methods.length; i < n; i ++) {
				Method method = methods[i];
				String methodName = method.getName();
				if (setterName.equals(methodName) && method.getParameterTypes().length == 1) {
					Object attr = convert(value, method.getParameterTypes()[0]);
					method.invoke(tag, new Object[]{attr});
					hasSetter = true;
					break;
				}
			}
			if (! hasSetter) { // 如果没有setter方法, 则检测动态属性注入.
				if (tag instanceof DynamicAttributes)
					((DynamicAttributes)tag).setDynamicAttribute(null, name, value); // TODO 名称空间未处理 <taga ns:attr="value" />
				else
					throw new JspException("no such method : " + setterName + " from " + tag.getClass().getName());
			}
		}
	}

	static Object convert(Object value, Class target) {
		if (value == null)
			return null;
		if (value.getClass() == target)
			return value;
		if (target == String.class)
			return String.valueOf(value);
		if (value instanceof String && ((String)value).length() > 0) {
			if (target == Character.class || target == Character.TYPE)
				return new Character(((String)value).charAt(0));
			if (target == Boolean.class || target == Boolean.TYPE)
				return Boolean.valueOf((String)value);
			if (target == Byte.class || target == Byte.TYPE)
				return new Byte((String)value);
			if (target == Short.class || target == Short.TYPE)
				return new Short((String)value);
			if (target == Integer.class || target == Integer.TYPE)
				return new Integer((String)value);
			if (target == Long.class || target == Long.TYPE)
				return new Long((String)value);
			if (target == Float.class || target == Float.TYPE)
				return new Float((String)value);
			if (target == Double.class || target == Double.TYPE)
				return new Double((String)value);
		}
		return value;
	}

}
