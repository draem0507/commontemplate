package org.commontemplate.standard.property.bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.TypeUtils;

public class ObjectToJsonPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		StringBuffer buf = new StringBuffer();
		appendObject(bean, buf);
		return buf.toString();
	}

	private void appendObject(Object bean, StringBuffer buf) throws Exception {
		if (bean == null
				|| bean.getClass().isPrimitive()
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
		} else if (bean.getClass().isArray()) {
			appendCollection(Arrays.asList((Object[])bean), buf);
		} else if (bean instanceof Collection) {
			appendCollection((Collection)bean, buf);
		} else if (bean instanceof Map) {
			appendMap((Map)bean, buf);
		} else {
			appendMap(ClassUtils.getObjectAllProperties(bean), buf);
		}
	}

	private void appendCollection(Collection collection, StringBuffer buf) throws Exception {
		buf.append("[");
		boolean isFirst = true;
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(iterator.next(), buf);
		}
		buf.append("]");
	}

	private void appendMap(Map properties, StringBuffer buf) throws Exception {
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
			appendObject(entry.getValue(), buf);
		}
		buf.append("}");
	}

	private String filterName(Object property) throws Exception {
		if (TypeUtils.isNamed(String.valueOf(property)))
			return String.valueOf(property);
		return "\"" + property + "\"";
	}

	private String filterValue(Object property) throws Exception {
		if (property instanceof CharSequence)
			return "\"" + property + "\"";
		return String.valueOf(property);
	}

}