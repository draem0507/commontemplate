package org.commontemplate.util;

/**
 * 数字比较器
 * <p/>
 * 混合比较Byte, Short, Integer, Long, Float, Double等
 * 
 * @author liangfei0201@163.com
 *
 */
public class NumberComprator implements java.util.Comparator, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public int compare(Object o1, Object o2) {
		Assert.assertTrue(o1 instanceof Number, "参数1必需为Number!");
		Assert.assertTrue(o2 instanceof Number, "参数2必需为Number!");
		
		Number n1 = (Number)o1;
		Number n2 = (Number)o2;
		if (n1 instanceof Byte || n2 instanceof Byte) {
			return n1.byteValue() - n2.byteValue();
		}
		if (n1 instanceof Short || n2 instanceof Short) {
			return n1.shortValue() - n2.shortValue();
		}
		if (n1 instanceof Integer || n2 instanceof Integer) {
			return n1.intValue() - n2.intValue();
		}
		if (n1 instanceof Long || n2 instanceof Long) {
			long l = n1.longValue() - n2.longValue();
			if (l == 0)
				return 0;
			return l < 0 ? -1 : 1;
		}
		if (n1 instanceof Float || n2 instanceof Float) {
			float d = n1.floatValue() - n2.floatValue();
			if (d == 0)
				return 0;
			return d < 0 ? -1 : 1;
		}
		double d = n1.doubleValue() - n2.doubleValue();
		if (d == 0)
			return 0;
		return d < 0 ? -1 : 1;
	}

}
