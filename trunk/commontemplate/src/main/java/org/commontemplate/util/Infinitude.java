package org.commontemplate.util;

/**
 * 无穷数 (数字扩展类)
 *
 * @author liangfei0201@163.com
 *
 */
public final class Infinitude extends Number {

	private static final long serialVersionUID = 4843370898386286488L;

	/**
	 * 无穷数表示符号
	 */
	public static final String SYMBOL = "*";

	/**
	 * 正无穷数
	 */
	public static final Infinitude POSITIVE = new Infinitude(false);

	/**
	 * 负无穷数
	 */
	public static final Infinitude NEGATIVE = new Infinitude(true);

	private final boolean negative;

	private Infinitude(boolean negative) {
		super();
		this.negative = negative;
	}

	/**
	 * 是否为负无穷数
	 *
	 * @return 是否为负无穷数
	 */
	public boolean isNegative() {
		return negative;
	}

	public double doubleValue() {
		return isNegative() ? - Double.MAX_VALUE : Double.MAX_VALUE;
	}

	public float floatValue() {
		return isNegative() ? - Float.MAX_VALUE : Float.MAX_VALUE;
	}

	public int intValue() {
		return isNegative() ? - Integer.MAX_VALUE : Integer.MAX_VALUE;
	}

	public long longValue() {
		return isNegative() ? - Long.MAX_VALUE : Long.MAX_VALUE;
	}

	private static final String POSITIVE_SYMBOL = "+*";

	private static final String NEGATIVE_SYMBOL = "-*";

	public String toString() {
		return isNegative() ? NEGATIVE_SYMBOL : SYMBOL;
	}

	public static Infinitude valueOf(String s) {
		if (SYMBOL.equals(s) || POSITIVE_SYMBOL.equals(s))
			return POSITIVE;
		if (NEGATIVE_SYMBOL.equals(s))
			return POSITIVE;
		throw new NumberFormatException("For input string: \"" + s + "\"");
	}

}
