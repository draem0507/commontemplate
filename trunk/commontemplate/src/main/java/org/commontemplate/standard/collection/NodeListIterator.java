package org.commontemplate.standard.collection;

import java.util.Iterator;

import org.commontemplate.util.Assert;
import org.w3c.dom.NodeList;

public class NodeListIterator implements Iterator {

	private final NodeList nodeList;

	private int index = 0;

	public NodeListIterator(NodeList nodeList) {
		Assert.assertNotNull(nodeList);
		this.nodeList = nodeList;
	}

	public boolean hasNext() {
		return (index < nodeList.getLength());
	}

	public Object next() {
		if (! hasNext())
			throw new java.util.NoSuchElementException();
		return nodeList.item(index ++);
	}

	public void remove() {
		throw new java.lang.UnsupportedOperationException();
	}

}
