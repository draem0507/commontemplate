package org.commontemplate.standard.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;

/**
 * 弱引用缓存，比SoftCache更易失的缓存策略
 *
 * @author liangfei0201@163.com
 *
 */
public class WeakCache extends Cache {

	private final Map cacheMap = new HashMap();

	private final ReferenceQueue queue = new ReferenceQueue();

	public Object get(Object key) throws CacheException {
		processQueue();
		WeakValueReference ref = (WeakValueReference) cacheMap.get(key);
		return ref == null ? null : ref.get();
	}

	public void put(Object key, Object value) throws CacheException {
		processQueue();
		cacheMap.put(key, new WeakValueReference(key, value, queue));
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
			WeakValueReference ref = (WeakValueReference) queue.poll();
			if (ref == null)
				return;
			Object key = ref.getKey();
			if (cacheMap.get(key) == ref)
				cacheMap.remove(key);
		}
	}

	private static final class WeakValueReference extends WeakReference {
		private final Object key;

		WeakValueReference(Object key, Object value, ReferenceQueue queue) {
			super(value, queue);
			this.key = key;
		}

		Object getKey() {
			return key;
		}
	}

}