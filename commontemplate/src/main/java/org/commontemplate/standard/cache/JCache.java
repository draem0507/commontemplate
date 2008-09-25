package org.commontemplate.standard.cache;

import javax.cache.CacheManager;

import org.commontemplate.config.CacheException;

/**
 * 与javax.cache缓存接口的适配器.<br>
 * javax.cache标准为JSR107:<br>
 * <a href="http://www.jcp.org/en/jsr/detail?id=107">http://www.jcp.org/en/jsr/detail?id=107</a><br>
 * 参见说明:<br>
 * <a href="https://jsr-107-interest.dev.java.net/javadoc/javax/cache/package-summary.html">https://jsr-107-interest.dev.java.net/javadoc/javax/cache/package-summary.html</a><br>
 *
 * @author liangfei0201@163.com
 *
 */
public class JCache extends org.commontemplate.config.Cache {

	private String cacheName;

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public Object get(Object key) throws CacheException {
		return CacheManager.getInstance().getCache(cacheName).get(key);
	}

	public void put(Object key, Object value) throws CacheException {
		CacheManager.getInstance().getCache(cacheName).put(key, value);
	}

	public void remove(Object key) throws CacheException {
		CacheManager.getInstance().getCache(cacheName).remove(key);
	}

	public void clear() throws CacheException {
		CacheManager.getInstance().getCache(cacheName).clear();
	}

}
