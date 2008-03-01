package org.commontemplate.standard.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;

/**
 * 软引用缓存，当内存不够时，VM将首先回收它们
 * 
 * 声明: 此类引自<a href="http://www.freemarker.org">FreeMarker</a>的freemarker.cache.SoftCacheStorage
 * 
 */
public class SoftCache implements Cache {

	private final Map cacheMap = new HashMap();

	private final ReferenceQueue queue = new ReferenceQueue();

	public Object get(Object key) throws CacheException {
		processQueue();
		SoftValueReference ref = (SoftValueReference) cacheMap.get(key);
		return ref == null ? null : ref.get();
	}

	public void put(Object key, Object value) throws CacheException {
		processQueue();
		cacheMap.put(key, new SoftValueReference(key, value, queue));
	}

	public void remove(Object key) throws CacheException {
		processQueue();
		cacheMap.remove(key);
	}

	public void clear() throws CacheException {
		cacheMap.clear();
		processQueue();
	}

	private void processQueue() {
		for (;;) {
			SoftValueReference ref = (SoftValueReference) queue.poll();
			if (ref == null)
				return;
			Object key = ref.getKey();
			if (cacheMap.get(key) == ref)
				cacheMap.remove(key);
		}
	}

	private static final class SoftValueReference extends SoftReference {
		private final Object key;

		SoftValueReference(Object key, Object value, ReferenceQueue queue) {
			super(value, queue);
			this.key = key;
		}

		Object getKey() {
			return key;
		}
	}

}