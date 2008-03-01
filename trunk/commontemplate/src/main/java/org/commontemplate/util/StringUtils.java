package org.commontemplate.util;

/**
 * 字符串工具类
 * 
 * @author liangfei0201@163.com
 * 
 */
public final class StringUtils {

	private StringUtils() {
	}

	private static final String OBJECT_SEPARATOR = ",";

	/**
	 * 对象转换成字符串
	 * 
	 * @param obj
	 *            对象
	 * @return 字符串
	 */
	public static String objectToString(Object obj) {
		if (obj.getClass().isArray()) // 格式化数组
			return arrayToString(obj, OBJECT_SEPARATOR);
		return String.valueOf(obj);
	}

	/**
	 * 数组转换成字符串
	 * 
	 * @param obj
	 *            对象
	 * @param separator
	 *            分隔符
	 * @return 字符串
	 */
	public static String arrayToString(Object obj, String separator) {
		Class clazz = obj.getClass();
		if (char[].class == clazz) {
			return String.valueOf((char[]) obj); // String.valueOf只重载了char[]数组类型
		} else if (boolean[].class == clazz) {
			boolean[] objs = (boolean[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else if (byte[].class == clazz) {
			byte[] objs = (byte[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else if (short[].class == clazz) {
			short[] objs = (short[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else if (int[].class == clazz) {
			int[] objs = (int[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else if (long[].class == clazz) {
			long[] objs = (long[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else if (float[].class == clazz) {
			float[] objs = (float[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else if (double[].class == clazz) {
			double[] objs = (double[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		} else {
			Object[] objs = (Object[]) obj;
			StringBuffer buf = new StringBuffer();
			for (int i = 0, n = objs.length; i < n; i++) {
				if (i != 0)
					buf.append(separator);
				buf.append(objs[i]);
			}
			return buf.toString();
		}
	}
}
