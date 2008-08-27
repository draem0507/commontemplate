package org.commontemplate.standard.coat;

/**
 * 注释及空白行过滤器.<br>
 * 过滤指令两端注释符，以及同行的空白。<br>
 *
 * @author liangfei0201@163.com
 *
 */
public class CommentSpacelineSyntaxCoatFilter extends CommentSyntaxCoatFilter {

	private static final long serialVersionUID = 1L;

	protected String trimEnd(String text) {
		text = super.trimEnd(text);
		for (int i = 0, n = text.length(); i < n; i ++) {
			char ch = text.charAt(i);
			if (ch == '\n') { // 如果遇到换行符，表示指令所在行没有其它内容，将空白去除
				text = text.substring(i + 1);
				break;
			} else if (! isSpace(ch)) { // 如果遇到非空白符，表示指令所在行有其它内容，不作处理
				break;
			}
		}
		return text;
	}

	protected String trimBegin(String text) {
		text = super.trimBegin(text);
		for (int i = text.length() - 1; i >= 0; i --) {
			char ch = text.charAt(i);
			if (ch == '\n') { // 如果遇到换行符，表示指令所在行没有其它内容，将空白去除(保留换行符)
				text = text.substring(0, i + 1);
				break;
			} else if (! isSpace(ch)) { // 如果遇到非空白符，表示指令所在行有其它内容，不作处理
				break;
			}
		}
		return text;
	}

	private boolean isSpace(char ch) {
		return (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\f' || ch == '\b');
	}

}
