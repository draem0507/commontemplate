package org.commontemplate.standard.cache;

import org.commontemplate.config.CacheException;
import org.commontemplate.util.Assert;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * <a href="http://oscache.opensymphony.com">Oscache</a>缓存适配器，需oscache.jar包支持
 *
 * @author liangfei0201@63.com
 *
 */
public class OSCache extends org.commontemplate.config.Cache {

	private static final String GROUP = "CommonTemplateCache";

	private final com.opensymphony.oscache.base.Cache cache;

	public OSCache() {
		final GeneralCacheAdministrator manager = new GeneralCacheAdministrator();
		this.cache = manager.getCache();
		Assert.assertNotNull(this.cache, "OSCache.cache.no.such");
	}

	private int refreshPeriod = -1;

	/**
	 * 设置更新周期，单位：秒
	 *
	 * @param refreshPeriod
	 *            更新周期
	 */
	public void setRefreshPeriod(int refreshPeriod) {
		Assert.assertTrue(refreshPeriod > 0, "OSCache.refresh.period.less.than.zero");
		this.refreshPeriod = refreshPeriod;
	}

	public Object get(Object key) throws CacheException {
		try {
			return (refreshPeriod > 0) ? cache.getFromCache(String.valueOf(key), refreshPeriod)
					: cache.getFromCache(String.valueOf(key));
		} catch (NeedsRefreshException e) {
			cache.cancelUpdate(String.valueOf(key));
			return null;
		}
	}

	public void put(Object key, Object value) throws CacheException {
		cache.putInCache(String.valueOf(key), value, new String[] { GROUP });
	}

	public void remove(Object key) throws CacheException {
		try {
			cache.flushEntry(String.valueOf(key));
		} finally {
			cache.cancelUpdate(String.valueOf(key));
		}
	}

	public void clear() throws CacheException {
		cache.flushGroup(GROUP);
	}

}
