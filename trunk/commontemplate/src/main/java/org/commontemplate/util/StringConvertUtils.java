package org.commontemplate.util;

/**
 * 模板字符串转义符转换工具类, 在Java的基础上忽略不识别转义.
 * 使得revertLiteral可以完整的还原convertLiteral的字符串.
 *
 * @see org.commontemplate.util.JavaStringConvertUtils
 * @author liangfei0201@163.com
 */
public final class StringConvertUtils {

	private StringConvertUtils() {}

	/**
	 * 反斜杠转义字符，转义：\" \' \n \r \t \f \b \\uXXXX \\\\等字符，并忽略不识别的转义，如：\x
	 */
	public static String convertLiteral(String theString) {

		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len; x ++) {
			aChar = theString.charAt(x);
			if (aChar == '\\') {
				if (x < len - 1) {
					x ++;
					aChar = theString.charAt(x);
					if (aChar == 'u') { // 读取unicode
						if (x < len - 4) {
							char[] unicode = new char[]{theString.charAt(x + 1), theString.charAt(x + 2), theString.charAt(x + 3), theString.charAt(x + 4)};
							if (isUnicode(unicode)) {
								outBuffer.append(toUnicode(unicode));
								x += 4;
							} else {
								outBuffer.append('\\');
								outBuffer.append('u');
							}
						} else {
							outBuffer.append('\\');
							outBuffer.append('u');
						}
					} else {
						if (aChar == 't')
							outBuffer.append('\t');
						else if (aChar == 'r')
							outBuffer.append('\r');
						else if (aChar == 'n')
							outBuffer.append('\n');
						else if (aChar == 'f')
							outBuffer.append('\f');
						else if (aChar == 'b')
							outBuffer.append('\b');
						else if (aChar == '\"')
							outBuffer.append('\"');
						else if (aChar == '\'')
							outBuffer.append('\'');
						else if (aChar == '\\')
							outBuffer.append('\\');
						else {
							outBuffer.append('\\');
							outBuffer.append(aChar);
						}
					}
				} else {
					outBuffer.append('\\');
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	private static final String specialSaveChars = "\t\r\n\f\b";

	/**
	 * 还原反斜杠转义
	 */
	public static String revertLiteral(String theString) {
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len * 2);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			switch (aChar) {
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '\b':
				outBuffer.append('\\');
				outBuffer.append('b');
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					if (specialSaveChars.indexOf(aChar) != -1)
						outBuffer.append('\\');
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	private static boolean isUnicode(char[] unicode) {
		for (int i = 0; i < 4; i++) {
			switch (unicode[i]) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
					break;
				default:
					return false;
			}
		}
		return true;
	}

	private static char toUnicode(char[] unicode) {
		int value = 0;
		char aChar;
		for (int i = 0; i < 4; i++) {
			aChar = unicode[i];
			switch (aChar) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				value = (value << 4) + aChar - '0';
				break;
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
				value = (value << 4) + 10 + aChar - 'a';
				break;
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
				value = (value << 4) + 10 + aChar - 'A';
				break;
			default:
				throw new IllegalStateException("Malformed \\uxxxx encoding.");
			}
		}
		return (char)value;
	}

	/**
	 * Convert a nibble to a hex character
	 *
	 * @param nibble
	 *            the nibble to convert.
	 */
	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/** A table of hex digits */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
}
