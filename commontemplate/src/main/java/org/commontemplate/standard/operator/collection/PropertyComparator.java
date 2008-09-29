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
		return reset(compareProperty(o1, o2, property));
	}

	public static int compareProperty(Object o1, Object o2, String property) {
		
		try {
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
			// Ignore, return 0;
		}
		return (0);
	}
	
	/**
	 * 对于TreeSet，是不允许放入相同的元素的，而如何判断相同，是通过传入的 Comparator来决定的。<br>
	 * 所以，如果比较器发现比较的结果是 0 的时候，就应该返回 1 或 －1，以保证集合的内容不丢失。
	 * @see PropertyComparator
	 * @see PropertiesComparator
	 * @param comp
	 * @return
	 */
	public static int reset(int comp) {
		if(comp == 0) {
			return 1;
		}
		return comp;
	}

}
