package org.commontemplate.standard.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;
import org.commontemplate.util.Assert;

/**
 * 最近最少使用原则缓存
 * 
 * @author liangfei0201@163.com
 * 
 */
public class LruCache implements Cache {

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
				// 移除链表最前的位置的元素，get()方法已保证第一个位置上的元素为最近最少使用
				Object oldKey = keyList.removeFirst();
				cacheMap.remove(oldKey);
			} catch (IndexOutOfBoundsException e) {
				// ignore
			}
		}
	}

	public Object get(Object key) throws CacheException {
		Object result = cacheMap.get(key);
		// 当某个元素被命中时，将其调整到链表未尾，保证链表最前的元素就是最近最少使用的元素
		keyList.remove(key);
		if (result != null) 
			keyList.add(key);
		return result;
	}

	public void remove(Object key) throws CacheException {
		keyList.remove(key);
		cacheMap.remove(key);
	}

	public void clear() throws CacheException {
		cacheMap.clear();
		keyList.clear();
	}

	public String toString() {
		return "LruCache: " + maxSize;
	}

}
