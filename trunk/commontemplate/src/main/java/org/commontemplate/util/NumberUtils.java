package org.commontemplate.util;

public final class NumberUtils {

	private NumberUtils() {}

	public static final Integer INTEGER_ZERO = new Integer(0);

	public static final Integer INTEGER_POSITIVE_ONE = new Integer(1);

	public static final Integer INTEGER_NEGATIVE_ONE = new Integer(-1);

	public static Number parseNumber(String name) {
		char endChar = name.charAt(name.length() - 1);
		if (name.startsWith("0x")) // 十六进制数
			return new Integer(Integer.parseInt(name.substring(2), 16));
		else if (endChar >= '0' && endChar <= '9')
			if (name.indexOf('.') != -1) // 有点号无后缀默认为Double
				return new Double(name);
			else
				if (name.charAt(0) == '0') // 八进制数
					return new Integer(Integer.parseInt(name, 8));
				else
					return new Integer(name); // 无点号无后缀默认为Integer
		else if (endChar == 'b' || endChar == 'B')
			return new Byte(name.substring(0, name.length() - 1));
		else if (endChar == 's' || endChar == 'S')
			return new Short(name.substring(0, name.length() - 1));
		else if (endChar == 'i' || endChar == 'I')
			return new Integer(name.substring(0, name.length() - 1));
		else if (endChar == 'l' || endChar == 'L')
			return new Long(name.substring(0, name.length() - 1));
		else if (endChar == 'd' || endChar == 'D')
			return new Double(name.substring(0, name.length() - 1));
		else if (endChar == 'f' || endChar == 'F')
			return new Float(name.substring(0, name.length() - 1));
		throw new NumberFormatException("解析数字\"" + name + "\"失败!");
	}

}
