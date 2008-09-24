package org.commontemplate.standard.collection;

import java.io.Serializable;
import java.util.Collection;

import org.w3c.dom.NodeList;

public class NodeListCollectionConverter implements CollectionConverter, Serializable {

	private static final long serialVersionUID = 1L;

	public Collection convert(Object data) {
		return new NodeListCollection((NodeList)data);
	}

}
