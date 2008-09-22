package org.commontemplate.standard.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.commontemplate.config.CacheException;
import org.commontemplate.util.Assert;

/**
 * <a href="http://ehcache.sourceforge.net">EHCache</a>缓存适配器，需ehcache.jar包支持
 *
 * @author liangfei0201@163.com
 *
 */
public class EHCache extends org.commontemplate.config.Cache {

	private static final String GROUP = "CommonTemplateCache";

	private final net.sf.ehcache.Cache cache;

	public EHCache() {
		final CacheManager manager = CacheManager.getInstance();
		net.sf.ehcache.Cache c = manager.getCache(GROUP);
		if (c == null) {
			manager.addCache(GROUP);
			c = manager.getCache(GROUP);
		}
		this.cache = c;
		Assert.assertNotNull(this.cache, "EHCache.cache.no.such");
	}

	public Object get(Object key) throws CacheException {
		Element element = cache.get(key);
		if (element == null)
			return null;
		return element.getObjectValue();
	}

	public void put(Object key, Object value) throws CacheException {
		cache.put(new Element(key, value));
	}

	public void remove(Object key) throws CacheException {
		cache.remove(key);
	}

	public void clear() throws CacheException {
		cache.removeAll();
	}

}
