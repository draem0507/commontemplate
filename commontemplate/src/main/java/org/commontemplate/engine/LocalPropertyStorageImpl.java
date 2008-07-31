package org.commontemplate.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.PropertyStorage;

/**
 * 局部对象存储器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class LocalPropertyStorageImpl implements PropertyStorage {

	private final LocalContext superLocalContext;

	private final Context context;

	LocalPropertyStorageImpl(LocalContext superLocalContext, Context context) {
		this.superLocalContext = superLocalContext;
		this.context = context;
	}

	private final Map defaultObjectContainer = new HashMap();

	public Object getProperty(String name) {
		Object o = defaultObjectContainer.get(name);
		if (o != null)
			return o;
		if (superLocalContext != null)
			return superLocalContext.getProperty(name);
		return context.getGlobalContext().getProperty(name);
	}

	public void putProperty(String name, Object value) {
		defaultObjectContainer.put(name, value);
	}

	public void removeProperty(String name) {
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

	public Object getProperty(String type, String name) {
		Object o = getObjectContainer(type).get(name);
		if (o != null)
			return o;
		if (superLocalContext != null)
			return superLocalContext.getProperty(type, name);
		return context.getGlobalContext().getProperty(type, name);
	}

	public void putProperty(String type, String name, Object value) {
		getObjectContainer(type).put(name, value);
	}

	public void removeProperty(String type, String name) {
		getObjectContainer(type).remove(name);
	}

	public void clearProperties() {
		defaultObjectContainer.clear();
		for (Iterator iterator = objectContainers.values().iterator(); iterator.hasNext();) {
			((Map)iterator.next()).clear();
		}
		objectContainers.clear();
	}

	public synchronized Map getProperties(String type) {
		return getObjectContainer(type);
	}

}
