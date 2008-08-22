package org.commontemplate.standard.converter;

import java.util.Arrays;
import java.util.Collection;

public class ArrayCollectionConverter implements CollectionConverter {

	public Collection convert(Object data) {
		return Arrays.asList((Object[]) data);
	}

}
