package org.commontemplate.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.PropertyStorage;

/**
 * 全局对象存储器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class GlobalPropertyStorageImpl implements PropertyStorage {

	private final Map defaultObjectContainer = new HashMap();

	GlobalPropertyStorageImpl() {

	}

	public synchronized Object getProperty(String name) {
		Object o = defaultObjectContainer.get(name);
		if (o != null)
			return o;
		return null;
	}

	public synchronized void putProperty(String name, Object value) {
		defaultObjectContainer.put(name, value);
	}

	public synchronized void removeProperty(String name) {
		defaultObjectContainer.remove(name);
	}

	public Map getProperties() {
		return defaultObjectContainer;
	}

	// 对象分类管理 ------------

	private final Map objectContainers = new HashMap();

	private synchronized Map getObjectContainer(String type) {
		Map objectContainer = (Map)objectContainers.get(type);
		if (objectContainer == null) {
			objectContainer = new HashMap();
			objectContainers.put(type, objectContainer);
		}
		return objectContainer;
	}

	public synchronized Object getProperty(String type, String name) {
		return getObjectContainer(type).get(name);
	}

	public synchronized void putProperty(String type, String name, Object value) {
		getObjectContainer(type).put(name, value);
	}

	public synchronized void removeProperty(String type, String name) {
		getObjectContainer(type).remove(name);
	}

	public synchronized void clearProperties() {
		defaultObjectContainer.clear();
		for (Iterator iterator = objectContainers.values().iterator(); iterator.hasNext();)
			((Map)iterator.next()).clear();
		objectContainers.clear();
	}

	public synchronized Map getProperties(String type) {
		return getObjectContainer(type);
	}

}
