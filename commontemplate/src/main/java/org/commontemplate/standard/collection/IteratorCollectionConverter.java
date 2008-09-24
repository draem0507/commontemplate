package org.commontemplate.standard.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorCollectionConverter implements CollectionConverter, Serializable {

	private static final long serialVersionUID = 1L;

	public Collection convert(Object data) {
		List list = new LinkedList();
		for (Iterator i = (Iterator)data; i.hasNext();)
			list.add(i.next());
		return list;
	}

}
