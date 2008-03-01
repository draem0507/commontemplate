package org.commontemplate.standard.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;
import org.commontemplate.util.Assert;

/**
 * 先进先出原则缓存
 * 
 * @author liangfei0201@163.com
 * 
 */
public class FifoCache implements Cache {

	private final Map cacheMap = new HashMap();

	private final LinkedList keyList = new LinkedList();

	private int maxSize = 100;

	public void setMaxSize(int maxSize) {
		Assert.assertTrue(maxSize > 0);
		this.maxSize = maxSize;
	}

	public void put(Object key, Object value) throws CacheException {
		cacheMap.put(key, value);
		keyList.add(key);
		if (keyList.size() > maxSize) {
			try {
				// 移除链表最前的位置的元素
				Object oldKey = keyList.removeFirst();
				cacheMap.remove(oldKey);
			} catch (IndexOutOfBoundsException e) {
				// ignore
			}
		}
	}

	public Object get(Object key) throws CacheException {
		return cacheMap.get(key);
	}

	public void remove(Object key) throws CacheException {
		cacheMap.remove(key);
		keyList.remove(key);
	}

	public void clear() throws CacheException {
		cacheMap.clear();
		keyList.clear();
	}

	public String toString() {
		return "FifoCache: " + maxSize;
	}

}
