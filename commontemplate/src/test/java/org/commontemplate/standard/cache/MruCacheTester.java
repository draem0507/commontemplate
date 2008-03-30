package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class MruCacheTester extends CacheTester {

	protected Cache newCache() {
		MruCache cache = new MruCache();
		cache.setMaxSize(3);
		return cache;
	}

}
