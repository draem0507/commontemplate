package org.commontemplate.standard.coat;

import org.commontemplate.config.TextFilter;

/**
 * 注释语法外套过滤器.<br>
 * 过滤指令两端注释符.<br>
 * 如：<br>
 * HTML: &lt;!--$if--&gt;<br>
 * JAVA: / * $if * /<br>
 *
 * @author liangfei0201@163.com
 *
 */
public class CommentSyntaxCoatFilter implements TextFilter, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private boolean commentSyntaxCoatAvailable = true;

	public boolean isCommentSyntaxCoatAvailable() {
		return commentSyntaxCoatAvailable;
	}

	public void setCommentSyntaxCoatAvailable(boolean commentSyntaxCoatAvailable) {
		this.commentSyntaxCoatAvailable = commentSyntaxCoatAvailable;
	}

	// 注释起始符
	private String begin = "";

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		if (begin == null)
			return;
		this.begin = begin;
	}

	// 注释结束符
	private String end = "";

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		if (end == null)
			return;
		this.end = end;
	}

	// 当指令所在行没有其它内容时，清除该空白行
	private boolean clearSpaceline;

	public boolean isClearSpaceline() {
		return clearSpaceline;
	}

	public void setClearSpaceline(boolean clearSpaceline) {
		this.clearSpaceline = clearSpaceline;
	}

	public String filter(String text, boolean isFirst, boolean isLast) {
		if (! commentSyntaxCoatAvailable)
			return text;
		if (text.startsWith(end)) {
			text = trimEnd(text);
			if (clearSpaceline)
				text = clearEnd(text);
		}
		if (text.endsWith(begin)) {
			text = trimBegin(text);
			if (clearSpaceline)
				text = clearBegin(text);
		}
		return text;
	}

	protected String trimEnd(String text) {
		return text.substring(end.length());
	}

	protected String trimBegin(String text) {
		return text.substring(0, text.length() - begin.length());
	}

	protected String clearEnd(String text) {
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

	protected String clearBegin(String text) {
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
