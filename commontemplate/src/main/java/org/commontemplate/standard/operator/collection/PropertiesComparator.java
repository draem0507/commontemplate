package org.commontemplate.standard.operator.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 多属性比较器
 *
 * @author liangfei0201@163.com
 *
 */
public class PropertiesComparator implements Comparator, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private final List propertyList;

	public PropertiesComparator(List propertyList) {
		this.propertyList = propertyList;
	}

	public int compare(Object o1, Object o2) {
		for (Iterator iterator = propertyList.iterator(); iterator.hasNext();) {
			String property = (String)iterator.next();
			int comp = PropertyComparator.compareProperty(o1, o2, property);
			if (comp != 0)
				return comp;
		}
		return PropertyComparator.reset(0);
	}

}
