package org.commontemplate.standard.collection;

import java.util.Iterator;

import org.commontemplate.util.Assert;
import org.commontemplate.util.CollectionSupport;
import org.w3c.dom.NodeList;

public class NodeListCollection extends CollectionSupport {

	private static final long serialVersionUID = 1L;

	private final NodeList nodeList;

	public NodeListCollection(NodeList nodeList) {
		Assert.assertNotNull(nodeList);
		this.nodeList = nodeList;
	}

	public Iterator iterator() {
		return new NodeListIterator(nodeList);
	}

	public int size() {
		return nodeList.getLength();
	}

}
