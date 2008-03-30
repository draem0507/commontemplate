package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class WeakCacheTester extends CacheTester {

	protected Cache newCache() {
		return new WeakCache();
	}

}
