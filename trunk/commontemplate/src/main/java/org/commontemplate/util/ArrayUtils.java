package org.commontemplate.util;

/**
 * 数组工具包
 * 
 * @author liangfei0201@163.com
 *
 */
public final class ArrayUtils {
	
	private ArrayUtils() {}
	
	public static String[] castObjectToString(Object[] objs) {
		if (objs == null)
			return null;
		if (objs.length == 0)
			return new String[0];
		String[] strs = new String[objs.length];
		for (int i = 0, n = objs.length; i < n; i ++) {
			strs[i] = (String)objs[i];
		}
		return strs;
	}

}
