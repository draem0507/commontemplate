package org.commontemplate.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionUtils {

	private CollectionUtils() {}

	public static Map getConcurrentMap() {
		if (JdkVersion.isAtLeastJava15()) {
			try {
				return (Map)Class.forName("java.util.concurrent.ConcurrentHashMap").newInstance();
			} catch (Exception e) {
				// ignore
			}
		}
		return Collections.synchronizedMap(new HashMap());
	}

}
