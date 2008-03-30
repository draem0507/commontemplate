package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class FifoCacheTester extends CacheTester {

	protected Cache newCache() {
		FifoCache cache = new FifoCache();
		cache.setMaxSize(3);
		return cache;
	}

	/**
	 * 测试缓存溢出策略
	 */
	public void testCacheOverflow() {
		cache.put("aa", "xx");
		cache.put("bb", "yy");
		cache.get("aa"); // 保证与调用次数无关
		cache.put("cc", "zz");
		cache.put("dd", "ww");
		assertNull(cache.get("aa"));
		assertEquals("yy", cache.get("bb"));
		assertEquals("zz", cache.get("cc"));
		assertEquals("ww", cache.get("dd"));
	}

}
