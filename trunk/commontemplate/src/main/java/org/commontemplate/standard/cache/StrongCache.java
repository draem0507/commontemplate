package org.commontemplate.standard.cache;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;

/**
 * 强引用缓存，总不过期，简单的HashMap缓存
 * 
 * @author liangfei@163.com
 * 
 */
public class StrongCache implements Cache {

	private final Map cacheMap = new HashMap();

	public Object get(Object key) throws CacheException {
		return cacheMap.get(key);
	}

	public void put(Object key, Object value) throws CacheException {
		cacheMap.put(key, value);
	}

	public void remove(Object key) throws CacheException {
		cacheMap.remove(key);
	}

	public void clear() throws CacheException {
		cacheMap.clear();
	}

}
