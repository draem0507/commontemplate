package org.commontemplate.standard.property.array;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ArraySortPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		if (bean instanceof boolean[]) {
			boolean[] array = (boolean[])bean;
			int len = array.length;
			int count = 0;
			for (int i = 0; i < len; i ++) {
				if (! array[i])
					count ++;
			}
			boolean[] dest = new boolean[len];
			if (count > 0)
				Arrays.fill(dest, count, len, true); // 将true排在后面
			return dest;
		} else if (bean instanceof byte[]) {
			byte[] array = (byte[])bean;
			int len = array.length;
			byte[] dest = new byte[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else if (bean instanceof char[]) {
			char[] array = (char[])bean;
			int len = array.length;
			char[] dest = new char[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else if (bean instanceof short[]) {
			short[] array = (short[])bean;
			int len = array.length;
			short[] dest = new short[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else if (bean instanceof int[]) {
			int[] array = (int[])bean;
			int len = array.length;
			int[] dest = new int[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else if (bean instanceof long[]) {
			long[] array = (long[])bean;
			int len = array.length;
			long[] dest = new long[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else if (bean instanceof float[]) {
			float[] array = (float[])bean;
			int len = array.length;
			float[] dest = new float[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else if (bean instanceof double[]) {
			double[] array = (double[])bean;
			int len = array.length;
			double[] dest = new double[len];
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort(dest);
			return dest;
		} else {
			Object array = bean;
			int len = Array.getLength(array);
			Object dest = Array.newInstance(array.getClass().getComponentType(), len);
			System.arraycopy(array, 0, dest, 0, len);
			Arrays.sort((Object[])dest);
			return dest;
		}
	}

}
