package org.commontemplate.standard.converter;

import java.util.Collection;

import org.w3c.dom.NodeList;

public class NodeListCollectionConverter implements CollectionConverter {

	public Collection convert(Object data) {
		return new NodeListCollection((NodeList)data);
	}

}