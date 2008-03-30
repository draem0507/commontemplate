package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class LruCacheTester extends CacheTester {

	protected Cache newCache() {
		LruCache cache = new LruCache();
		cache.setMaxSize(3);
		return cache;
	}

	public void testCacheOverflow() {
		cache.put("aa", "xx");
		cache.put("bb", "yy");
		cache.put("cc", "zz");
		cache.get("aa"); // 调用aa和cc, 使bb变成最少使用
		cache.get("cc");
		cache.put("dd", "ww");
		assertNull(cache.get("bb")); // 断言最少使用的被溢掉
		assertEquals("xx", cache.get("aa"));
		assertEquals("zz", cache.get("cc"));
		assertEquals("ww", cache.get("dd"));
	}

}
