package org.commontemplate.standard.directive.filter.code;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

/**
 * 属性代码着色.<br>
 * 使用如:
 * <pre>
 * $code{properties}
 * xxx=yyy
 * $end
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class PropertiesCodeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String text) {
		// 前置条件
		if (text == null)
			return null;
		text = text.trim();
		if (text.length() == 0)
			return text;
		// 增加前导换行符
		if (! text.startsWith("\n") && ! text.startsWith("\r\n"))
			text = "\n" + text;
		// 转义HTML标签起止符
		text = text.replaceAll("\\<", "&lt;");
		text = text.replaceAll("\\>", "&gt;");
		// 替换键值对
		text = text.replaceAll("([ |\t]*\r?\n)([^\\#|\\!|\r|\n][^\\=|\\:|\n]*)([\\=|\\:])([^\r|\n]*)",
				"$1<font color=\"" + keyColor + "\">$2</font><font color=\""
				+ separatorColor + "\">$3</font><font color=\""
				+ valueColor + "\">$4</font>");
		// 替换注释
		text = text.replaceAll("([ |\t]*\r?\n)([\\#|\\!][^\r|\n]*)",
				"$1<font color=\"" + commentColor + "\">$2</font>");
		// 去掉前导换行符
		text = text.replaceAll("^[\r|\n]*", "");
		// 转义HTML换行标签
		text = text.replaceAll("\r?\n", "<br/>$0");
		return text;
	}

	private String keyColor = "purple";

	private String separatorColor = "black";

	private String valueColor = "blue";

	private String commentColor = "gray";

	public String getKeyColor() {
		return keyColor;
	}

	public void setKeyColor(String keyColor) {
		if (keyColor != null)
			this.keyColor = keyColor;
	}

	public String getSeparatorColor() {
		return separatorColor;
	}

	public void setSeparatorColor(String separatorColor) {
		if (separatorColor != null)
			this.separatorColor = separatorColor;
	}

	public String getValueColor() {
		return valueColor;
	}

	public void setValueColor(String valueColor) {
		if (valueColor != null)
			this.valueColor = valueColor;
	}

	public String getCommentColor() {
		return commentColor;
	}

	public void setCommentColor(String commentColor) {
		if (commentColor != null)
			this.commentColor = commentColor;
	}

}
