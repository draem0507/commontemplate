package org.commontemplate.standard.collection;

import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class EnumerationCollectionConverter implements CollectionConverter {

	public Collection convert(Object data) {
		List list = new LinkedList();
		for (Enumeration e = (Enumeration)data; e.hasMoreElements();)
			list.add(e.nextElement());
		return list;
	}

}
