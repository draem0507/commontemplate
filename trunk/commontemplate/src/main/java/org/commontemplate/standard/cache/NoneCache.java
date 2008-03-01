package org.commontemplate.standard.cache;

import org.commontemplate.config.CacheException;
import org.commontemplate.config.Cache;

/**
 * 不缓存
 * 
 * @author liangfei0201@163.com
 * 
 */
public class NoneCache implements Cache {

	public Object get(Object key) throws CacheException {
		return null;
	}

	public void put(Object key, Object value) throws CacheException {

	}

	public void remove(Object key) throws CacheException {

	}

	public void clear() throws CacheException {

	}

}
