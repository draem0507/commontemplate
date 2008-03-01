package org.commontemplate.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.ObjectStorage;

/**
 * 全局对象存储器实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class GlobalObjectStorageImpl implements ObjectStorage {
	
	private final Map defaultObjectContainer = new HashMap();
	
	GlobalObjectStorageImpl() {
		
	}

	public synchronized Object lookupObject(String name) {
		Object o = defaultObjectContainer.get(name);
		if (o != null) 
			return o;
		return null;
	}

	public synchronized void putObject(String name, Object value) {
		defaultObjectContainer.put(name, value);
	}

	public synchronized void removeObject(String name) {
		defaultObjectContainer.remove(name);
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

	public synchronized Object lookupObject(String type, String name) {
		return getObjectContainer(type).get(name);
	}

	public synchronized void putObject(String type, String name, Object value) {
		getObjectContainer(type).put(name, value);
	}

	public synchronized void removeObject(String type, String name) {
		getObjectContainer(type).remove(name);
	}

	public synchronized void clearObjects() {
		defaultObjectContainer.clear();
		for (Iterator iterator = objectContainers.values().iterator(); iterator.hasNext();)
			((Map)iterator.next()).clear();
		objectContainers.clear();
	}
}
