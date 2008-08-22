package org.commontemplate.standard.converter;

import java.util.Collection;
import java.util.Map;

public class MapCollectionConverter implements CollectionConverter {

	public Collection convert(Object data) {
		return ((Map) data).entrySet();
	}

}
