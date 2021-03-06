package org.commontemplate.config;

import java.io.Serializable;

import org.commontemplate.util.Assert;

/**
 * 语法设置类 (不变类，线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class Syntax implements Serializable {

	private static final long serialVersionUID = 1L;

	// 指令语法 -----

	private final char directiveLeader;

	private final char expressionBegin;

	private final char expressionEnd;

	// 特殊指令 ------

	private final char lineComment;

	private final char blockComment;

	private final char noParse;

	private final String endDirectiveName;

	/**
	 * 定义指令语法及特殊指令
	 *
	 * @param directiveLeader
	 * @param expressionBegin
	 * @param expressionEnd
	 * @param lineComment
	 * @param blockComment
	 * @param noParse
	 * @param endDirectiveName
	 */
	public Syntax(char directiveLeader, char expressionBegin, char expressionEnd,
			char lineComment, char blockComment, char noParse,
			String endDirectiveName) {
		Assert.assertNotEmpty(endDirectiveName, "结束指令名不能为空!"); // TODO 未国际化
		assertMutex(new char[] { directiveLeader, expressionBegin, expressionEnd,
				noParse, lineComment, blockComment });
		this.directiveLeader = directiveLeader;
		this.expressionBegin = expressionBegin;
		this.expressionEnd = expressionEnd;
		this.noParse = noParse;
		this.lineComment = lineComment;
		this.blockComment = blockComment;
		this.endDirectiveName = endDirectiveName;
	}

	private final void assertMutex(char[] chs) {
		int n = chs.length;

		for (int i = 0; i < n; i++) {
			if (chs[i] > (char) 127
					|| (chs[i] >= '0' && chs[i] <= '9' || chs[i] == '.')
					|| chs[i] == '_' || (chs[i] >= 'a' && chs[i] <= 'z')
					|| (chs[i] >= 'A' && chs[i] <= 'Z') || chs[i] == ' '
					|| chs[i] == '\t' || chs[i] == '\n' || chs[i] == '\r'
					|| chs[i] == '\\' || chs[i] == '\"' || chs[i] == '\''
					|| chs[i] == '`')
				throw new IllegalArgumentException(
						"语法中特征字符必需为符号，而不能是字母，下划线，点号，数字，引号，空白，转义符等！错误发生在出现非法符号："
								+ chs[i]); // TODO 未国际化

			for (int j = i + 1; j < n; j++) {
				if (chs[i] == chs[j])
					throw new IllegalStateException("语法中特征字符不能相同！错误发生在出现多个："
							+ chs[i]); // TODO 未国际化
			}
		}
	}

	public final char getDirectiveLeader() {
		return directiveLeader;
	}

	public final char getExpressionBegin() {
		return expressionBegin;
	}

	public final char getExpressionEnd() {
		return expressionEnd;
	}

	public final char getLineComment() {
		return lineComment;
	}

	public final char getBlockComment() {
		return blockComment;
	}

	public final char getNoParse() {
		return noParse;
	}

	public final String getEndDirectiveName() {
		return endDirectiveName;
	}

	public final String getEndDirective() {
		return String.valueOf(getDirectiveLeader()) + getEndDirectiveName();
	}

	// Default Syntax ------------

	public static final char DEFAULT_DIRECTIVE_LEADER = '$';

	public static final char DEFAULT_EXPRESSION_BEGIN = '{';

	public static final char DEFAULT_EXPRESSION_END = '}';

	public static final char DEFAULT_LINE_COMMENT = '#';

	public static final char DEFAULT_BLOCK_COMMENT = '*';

	public static final char DEFAULT_NO_PARSE = '!';

	public static final String DEFAULT_END_DIRECTIVE_NAME = "end";

	public static final Syntax DEFAULT = new Syntax(
			DEFAULT_DIRECTIVE_LEADER, DEFAULT_EXPRESSION_BEGIN,
			DEFAULT_EXPRESSION_END, DEFAULT_LINE_COMMENT,
			DEFAULT_BLOCK_COMMENT, DEFAULT_NO_PARSE,
			DEFAULT_END_DIRECTIVE_NAME);

}
