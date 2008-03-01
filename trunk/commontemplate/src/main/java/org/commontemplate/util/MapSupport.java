package org.commontemplate.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Map基类
 * (功能与java.util.AbstractMap类似, 只是提供另一种模板方法模式)
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class MapSupport implements Map {
	
	public MapSupport() {
		
	}
	
	public int size() {
		return keySet().size();
	}

	public boolean isEmpty() {
		return keySet().size() == 0;
	}

	public boolean containsKey(Object key) {
		return keySet().contains(key);
	}

	public boolean containsValue(Object value) {
		return values().contains(value);
	}

	public Object get(Object key) {
		return getValue(key);
	}

	public Object put(Object key, Object value) {
		Object old = getValue(key);
		putValue(key, value);
		return old;
	}

	public Object remove(Object key) {
		Object old = getValue(key);
		removeValue(key);
		return old;
	}

	public void putAll(Map map) {
		Iterator i = map.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry)i.next();
			putValue(entry.getKey(), entry.getValue());
		}
	}

	public void clear() {
		Iterator i = keySet().iterator();
		while (i.hasNext()) {
			removeValue(i.next());
		}
	}

	public Set keySet() {
		Set keySet = new HashSet();
		Enumeration names = getNames();
		while (names.hasMoreElements()) {
			keySet.add(names.nextElement());
		}
		return keySet;
	}

	public Collection values() {
		List list = new ArrayList();
		Enumeration names = getNames();
		while (names.hasMoreElements()) {
			list.add(getValue(names.nextElement()));
		}
		return list;
	}

	public Set entrySet() {
		Set entrySet = new HashSet();
		Enumeration names = getNames();
		while (names.hasMoreElements()) {
			Object name = names.nextElement();
			entrySet.add(new MapEntry(name, getValue(name)));
		}
		return entrySet;
	}
	
	public String toString() {
		return entrySet().toString();
	}
	
	protected abstract Enumeration getNames();

	protected abstract Object getValue(Object key);

	protected void putValue(Object key, Object value) {
		// Ignore;
	}

	protected void removeValue(Object key) {
		// Ignore;
	}

}
