package org.commontemplate.standard.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class MapCollectionConverter implements CollectionConverter, Serializable {

	private static final long serialVersionUID = 1L;

	public Collection convert(Object data) {
		return ((Map) data).entrySet();
	}

}
