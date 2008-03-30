package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

import junit.framework.TestCase;

public abstract class CacheTester extends TestCase {

	protected Cache cache;

	protected abstract Cache newCache();

	protected void setUp() throws Exception {
		super.setUp();
		cache = newCache();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		cache = null;
	}

	/**
	 * 测试缓存加入
	 */
	public void testCachePut() {
		cache.put("test", "xx");
		assertEquals("xx", cache.get("test"));
	}

	/**
	 * 测试缓存移除
	 */
	public void testCacheRemove() {
		cache.put("test", "xx");
		cache.remove("test");
		assertNull(cache.get("test"));
	}

	/**
	 * 测试缓存清空
	 */
	public void testCacheClear() {
		cache.put("test", "xx");
		cache.clear();
		assertNull(cache.get("test"));
	}

}
