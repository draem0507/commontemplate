package org.commontemplate.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 将Enumeration接口适配为Iterator接口
 * 
 * @author liangfei0201@163.com
 */
public class EnumIterator implements Iterator {

	private final Enumeration enumeration;
	
	public EnumIterator(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}
	
	public Object next() {
		return enumeration.nextElement();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
