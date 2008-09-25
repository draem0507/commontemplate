package org.commontemplate.standard.property.array;

import java.util.Arrays;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ArrayAsListPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		if (bean instanceof boolean[]) {
			boolean[] array = (boolean[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = Boolean.valueOf(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof byte[]) {
			byte[] array = (byte[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Byte(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof char[]) {
			char[] array = (char[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Character(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof short[]) {
			short[] array = (short[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Short(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof int[]) {
			int[] array = (int[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Integer(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof long[]) {
			long[] array = (long[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Long(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof float[]) {
			float[] array = (float[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Float(array[i]);
			}
			return Arrays.asList(dest);
		} else if (bean instanceof double[]) {
			double[] array = (double[])bean;
			int len = array.length;
			Object[] dest = new Object[len];
			for (int i = 0; i < len; i ++) {
				dest[i] = new Double(array[i]);
			}
			return Arrays.asList(dest);
		} else {
			return Arrays.asList((Object[])bean);
		}
	}

}
