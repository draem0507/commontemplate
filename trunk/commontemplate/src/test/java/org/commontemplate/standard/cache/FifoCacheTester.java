package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class FifoCacheTester extends CacheTester {

	protected Cache newCache() {
		FifoCache cache = new FifoCache();
		cache.setMaxSize(2);
		return cache;
	}

	public void testCacheOverflow() {
		cache.put("aa", "xx");
		cache.put("bb", "yy");
		cache.put("cc", "zz");
		assertNull(cache.get("aa"));
		assertEquals("yy", cache.get("bb"));
		assertEquals("zz", cache.get("cc"));
	}

}
