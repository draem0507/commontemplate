package org.commontemplate.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.ObjectStorage;

/**
 * 局部对象存储器实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class LocalObjectStorageImpl implements ObjectStorage {

	private final LocalContext superLocalContext;

	private final Context context;
	
	LocalObjectStorageImpl(LocalContext superLocalContext, Context context) {
		this.superLocalContext = superLocalContext;
		this.context = context;
	}

	private final Map defaultObjectContainer = new HashMap();

	public Object lookupObject(String name) {
		Object o = defaultObjectContainer.get(name);
		if (o != null) 
			return o;
		if (superLocalContext != null) 
			return superLocalContext.lookupObject(name);
		return context.getGlobalContext().lookupObject(name);
	}

	public void putObject(String name, Object value) {
		defaultObjectContainer.put(name, value);
	}

	public void removeObject(String name) {
		defaultObjectContainer.remove(name);
	}

	private final Map objectContainers = new HashMap();
	
	private Map getObjectContainer(String type) {
		Map objectContainer = (Map)objectContainers.get(type);
		if (objectContainer == null) {
			objectContainer = new HashMap();
			objectContainers.put(type, objectContainer);
		}
		return objectContainer;
	}

	public Object lookupObject(String type, String name) {
		Object o = getObjectContainer(type).get(name);
		if (o != null) 
			return o;
		if (superLocalContext != null) 
			return superLocalContext.lookupObject(type, name);
		return context.getGlobalContext().lookupObject(type, name);
	}

	public void putObject(String type, String name, Object value) {
		getObjectContainer(type).put(name, value);
	}

	public void removeObject(String type, String name) {
		getObjectContainer(type).remove(name);
	}

	public void clearObjects() {
		defaultObjectContainer.clear();
		for (Iterator iterator = objectContainers.values().iterator(); iterator.hasNext();) {
			((Map)iterator.next()).clear();
		}
		objectContainers.clear();
	}

}
