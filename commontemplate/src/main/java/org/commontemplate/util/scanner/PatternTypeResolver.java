package org.commontemplate.util.scanner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.commontemplate.util.Assert;

/**
 * 模式匹配类型识别器
 *
 * "|" 或
 * "-" 连续
 * 如:{"_|a-z|A-Z", "0-9", "+|-|||&"}
 * 注: 未在列表中定义的字符将归为其它类型，索引为: 最大索引类型 + 1
 * 注: 按位置解析，所以 "|" 和 "-" 均不需要转义
 *
 * @author liangfei0201@163.com
 *
 */
public class PatternTypeResolver implements TypeResolver {

	public static final char DISCRETE_SEPARATOR = '|';

	public static final char SERIATE_SEPARATOR = '-';

	private static final char NULL_CHAR = '\0';

	private final Map types = new HashMap();

	private final Set scopes = new HashSet();

	private final int otherType;

	/**
	 * 构造模式匹配类型识别器
	 *
	 * @param patterns 字符类型模式
	 */
	public PatternTypeResolver(String[] patterns) {
		this.otherType = patterns.length;
		for (int i = 0, n = patterns.length; i < n; i ++) {
			String pattern = patterns[i];
			boolean isSeparator = false; // 保存是否为分割符位置
			boolean isSequence = false; // 保存是否在连接序列状态
			char buf = NULL_CHAR; // 用于缓存前一个非分割符字符
			for (int j = 0, m = pattern.length(); j < m; j ++) {
				char ch = pattern.charAt(j);
				if (isSeparator) {
					if (ch == SERIATE_SEPARATOR) {
						Assert.assertFalse(isSequence, " - 分割符位置不正确, 后面应该有一字符!");
						isSequence = true;
					} else if (ch == DISCRETE_SEPARATOR) {
						Assert.assertTrue(buf != NULL_CHAR, "含有无用的 | 分割符");
						types.put(new Character(buf), new Integer(i));
						buf = NULL_CHAR;
					} else {
						Assert.fail("字符之间应该有分割符!");
					}
				} else {
					if (isSequence) {
						Assert.assertTrue(buf != NULL_CHAR, " - 分割符位置不正确, 前面应该有一字符!");
						scopes.add(new CharScope(i, buf, ch));
						isSequence = false;
					} else {
						Assert.assertTrue(buf == NULL_CHAR, "字符位置错误!");
						buf = ch;
					}
				}
				isSeparator = (! isSeparator);
			}
			if (buf != NULL_CHAR) { // 回收最后的char
				types.put(new Character(buf), new Integer(i));
			}
		}
	}

	public int getType(char ch) {
		if (types.containsKey(new Character(ch)))
			return ((Integer)types.get(new Character(ch))).intValue();
		for (Iterator iterator = scopes.iterator(); iterator.hasNext();) {
			CharScope charScope = (CharScope)iterator.next();
			if (charScope.isMatching(ch))
				return charScope.getType();
		}
		return otherType;
	}

	private static final class CharScope {

		private int type;

		private char begin;

		private char end;

		public CharScope(int type, char begin, char end) {
			super();
			this.type = type;
			this.begin = begin;
			this.end = end;
		}

		public boolean isMatching(char ch) {
			return ch >= begin && ch <= end;
		}

		public int getType() {
			return type;
		}

		public String toString() {
			return begin + "-" + end + ":" + type;
		}

	}

}
