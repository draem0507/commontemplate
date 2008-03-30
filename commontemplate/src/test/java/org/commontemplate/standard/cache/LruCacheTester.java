package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class LruCacheTester extends CacheTester {

	protected Cache newCache() {
		LruCache cache = new LruCache();
		cache.setMaxSize(3);
		return cache;
	}

	/**
	 * 测试缓存最近溢出策略
	 */
	public void testCacheTimeOverflow() {
		cache.put("aa", "xx");
		cache.put("bb", "yy");
		cache.put("cc", "zz");
		cache.put("dd", "ww");
		assertNull(cache.get("aa"));
		assertEquals("yy", cache.get("bb"));
		assertEquals("zz", cache.get("cc"));
		assertEquals("ww", cache.get("dd"));
	}

	/**
	 * 测试缓存最少使用溢出策略
	 */
	public void testCacheCountOverflow() {
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
