package org.commontemplate.standard.operator.collection;

import java.util.Comparator;

import org.commontemplate.util.BeanUtils;

/**
 * 属性比较器
 *
 * @author liangfei0201@163.com
 *
 */
public class PropertyComparator implements Comparator, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private final String property;

	public PropertyComparator(String property) {
		this.property = property;
	}

	public int compare(Object o1, Object o2) {
		return compareProperty(o1, o2, property);
	}

	public static int compareProperty(Object o1, Object o2, String property) {
		try {
			if (o1 == null && o2 == null)
				return 0;
			if (o1 == null)
				return 1;
			if (o2 == null)
				return -1;
			if (property == null || property.length() == 0)
				return 0;
			boolean isDesc = false;
			if ('-' == property.charAt(0)) {
				isDesc = true;
				property = property.substring(1);
			} else if ('+' == property.charAt(0)) {
				property = property.substring(1);
			}
			Object pro1 = BeanUtils.getProperty(o1, property);
			Object pro2 = BeanUtils.getProperty(o2, property);
			if (pro1 instanceof java.lang.Comparable) {
				int comp = ((Comparable)pro1).compareTo(pro2);
				if (isDesc)
					return -(comp);
				return (comp);
			}
		} catch (Exception e) {
			// ignore, return 0;
		}
		return 0;
	}

}
