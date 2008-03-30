package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class NoneCacheTester extends CacheTester {

	protected Cache newCache() {
		return new NoneCache();
	}

	public void testCachePut() {
		cache.put("test", "xx");
		assertNull(cache.get("test"));
	}

}
