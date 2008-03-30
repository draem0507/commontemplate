package org.commontemplate.standard.cache;

import org.commontemplate.config.Cache;

public class SoftCacheTester extends CacheTester {

	protected Cache newCache() {
		return new SoftCache();
	}

}
