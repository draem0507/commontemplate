package org.commontemplate.standard.converter;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorCollectionConverter implements CollectionConverter {

	public Collection convert(Object data) {
		List list = new LinkedList();
		for (Iterator i = (Iterator)data; i.hasNext();)
			list.add(i.next());
		return list;
	}

}
