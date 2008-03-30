package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class StrongCacheTester extends CacheTester {

	protected Cache newCache() {
		return new StrongCache();
	}

}
