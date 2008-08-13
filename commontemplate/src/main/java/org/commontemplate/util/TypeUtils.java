package org.commontemplate.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;


public final class TypeUtils {

	private TypeUtils() {}

	public static boolean isTrue(final Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Boolean)
			return ((Boolean)obj).booleanValue();
		if (obj instanceof BigDecimal
				|| obj instanceof Double
				|| obj instanceof Float) // 加入对小数的支持
			return ((Number)obj).doubleValue() != 0;
		if (obj instanceof Number)
			return ((Number)obj).intValue() != 0;
		if (obj instanceof String)
			return ((String)obj).length() > 0;
		if (obj instanceof Collection)
			return ((Collection)obj).size() > 0;
		if (obj instanceof Map)
			return ((Map)obj).size() > 0;
		return true;
	}

	public static boolean isEmpty(final Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String)
			return ((String)obj).length() == 0;
		if (obj instanceof Collection)
			return ((Collection)obj).size() == 0;
		if (obj instanceof Map)
			return ((Map)obj).size() == 0;
		return false;
	}

	public static boolean isString(final String name) {
		if (name == null)
			return false;
		String t = name.trim();
		if (t.length() < 2) {
			return false;
		}
		if (t.startsWith("\"") || t.startsWith("\'") || t.startsWith("`")) {
			Assert.assertTrue(t.endsWith(t.substring(0, 1)));
			return true;
		}
		return false;
	}

	public static boolean isEscapeString(final String name) {
		if (name == null)
			return false;
		String t = name.trim();
		if (t.length() < 2) {
			return false;
		}
		if (t.startsWith("\"") || t.startsWith("\'")) {
			Assert.assertTrue(t.endsWith(t.substring(0, 1)));
			return true;
		}
		return false;
	}

	public static boolean isNoEscapeString(final String name) {
		if (name == null)
			return false;
		String t = name.trim();
		if (t.length() < 2) {
			return false;
		}
		if (t.startsWith("`")) {
			Assert.assertTrue(t.endsWith("`"));
			return true;
		}
		return false;
	}

	public static boolean isNumber(final String name) {
		if (name != null && name.length() > 0
				&& name.charAt(0) >= '0' && name.charAt(0) <= '9')
			return true;
		return false;
	}

	public static boolean isInteger(final String name) {
		if (name != null && name.length() > 0
				&& name.matches("^[0-9]+$"))
			return true;
		return false;
	}

	public static Integer parseInteger(final String name) {
		return new Integer(name);
	}

	public static boolean isSignNumber(final String name) {
		if (name != null && name.length() > 0
				&& name.charAt(0) >= '0' && name.charAt(0) <= '9')
			return true;
		if (name != null && name.length() > 1
				&& (name.charAt(0) == '+' || name.charAt(0) == '-')
				&& name.charAt(1) >= '0' && name.charAt(1) <= '9')
			return true;
		return false;
	}

	public static Number parseSignNumber(final String name) {
		char endChar = name.charAt(name.length() - 1);
		if (endChar >= '0' && endChar <= '9') {
			if (name.indexOf('.') != -1)
				return new Float(name);
			else
				return new Integer(name);
		} else if (endChar == 'S' || endChar == 's') {
			return new Short(name.substring(0, name.length() - 1));
		} else if (endChar == 'L' || endChar == 'l') {
			return new Long(name.substring(0, name.length() - 1));
		} else if (endChar == 'F' || endChar == 'f') {
			return new Float(name.substring(0, name.length() - 1));
		} else if (endChar == 'D' || endChar == 'd') {
			return new Double(name.substring(0, name.length() - 1));
		}
		throw new java.lang.IllegalArgumentException();
	}

	public static boolean isNamed(final String name) {
		return name != null && name.length() > 0
				&& name.matches("^[_|A-Z|a-z][_|0-9|A-Z|a-z]*$");
	}

	public static boolean isFunction(final String name) {
		if (name != null && name.length() > 1
				&& name.charAt(0) == '.' && isNamed(name.substring(1)))
			return true;
		return false;
	}

	public static boolean isOperator(final String name) {
		if (name == null)
			return false;
		String t = name.trim();
		if (t.length() == 0)
			return false;
		for (int i = 0, n = t.length(); i < n; i ++) {
			char ch = t.charAt(i);
			if (ch == '_' || ch == '\"' || ch == '\''
				|| (ch >= '0' && ch <='9')
				|| (ch >= 'A' && ch <='Z')
				|| (ch >= 'a' && ch <='z'))
				return false;
		}
		return true;
	}

	public static boolean isMultiOperator(String name) {
		return name != null && name.trim().length() > 1 && isOperator(name);
	}

	public static boolean isPrimitive(Class cls) {
		if (cls != Integer.TYPE && cls != Integer.class && cls != String.class
				&& cls != Boolean.TYPE && cls != Boolean.class
				&& cls != Short.TYPE && cls != Short.class && cls != Long.TYPE
				&& cls != Long.class && cls != Character.TYPE
				&& cls != Character.class && cls != Float.TYPE
				&& cls != Float.class && cls != Double.TYPE
				&& cls != Double.class && cls != Byte.TYPE && cls != Byte.class
				&& cls != File.class) {
			return false;
		}
		return true;
	}

	public static Object parseValue(String str) {
		if (str == null)
			return null;
		if ("true".equals(str))
			return Boolean.TRUE;
		if ("false".equals(str))
			return Boolean.FALSE;
		if (str.length() > 1 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"')
			return str.substring(1, str.length() - 1);
		if (str.length() > 1 && str.charAt(0) == '\'' && str.charAt(str.length() - 1) == '\'')
			return str.substring(1, str.length() - 1);
		if (TypeUtils.isSignNumber(str))
			return TypeUtils.parseSignNumber(str);
		return str;
	}

}
