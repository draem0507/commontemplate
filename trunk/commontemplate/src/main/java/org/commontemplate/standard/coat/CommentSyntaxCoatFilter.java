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

	private String begin = "";

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		if (begin == null)
			return;
		this.begin = begin;
	}

	private String end = "";

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		if (end == null)
			return;
		this.end = end;
	}

	public String filter(String text, boolean isFirst, boolean isLast) {
		if (text.startsWith(end))
			text = trimEnd(text);
		if (text.endsWith(begin))
			text = trimBegin(text);
		return text;
	}

	protected String trimEnd(String text) {
		return text.substring(end.length());
	}

	protected String trimBegin(String text) {
		return text.substring(0, text.length() - begin.length());
	}

}
