package org.commontemplate.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 将Iterator接口适配为Enumeration接口
 * 
 * @author liangfei0201@163.com
 *
 */
public class IterEnumeration implements Enumeration {
	
	private final Iterator iterator;

	public IterEnumeration(Iterator iterator) {
		this.iterator = iterator;
	}

	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	public Object nextElement() {
		return iterator.next();
	}

}
