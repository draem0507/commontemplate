package org.commontemplate.standard.directive.filter.code;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

/**
 * XML代码着色.<br>
 * 使用如:
 * <pre>
 * $code{xml}
 * &lt;html&gt;
 *     &lt;body&gt;
 *     &lt;/body&gt;
 * &lt;/html&gt;
 * $end
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class XmlCodeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private String textColor = null;

	private String commentColor = "#3f5fbf";

	private String tagColor = "#3f7f5f";

	private String attributeColor = "#7f0055";

	private String equalColor = "#000000";

	private String stringColor = "#2a00ff";

	private String entityColor = "#2a00ff";

	private String dataColor = "#3f7f5f";

	public String filter(String text) {
		String result = text;
		// 过滤&符号
		result = result.replaceAll("&", "&amp;");
		// 过滤CDATA //TODO 应该文本化CDATA里面的标签
		result = result.replaceAll(
						"<!\\[CDATA\\[([^(\\]\\]\\>)]*)\\]\\]>",
						"\1&lt;![CDATA[\0$1\1]]&gt;\0");
		// 过滤注释
		result = result.replaceAll("<!--([^(\\-\\->)]*)-->",
						"\2&lt;!--$1--&gt;\0");
		// 过滤标签
		result = result.replaceAll("<([^(<|>)]+)>",
				"\3&lt;$1&gt;</font>");
		// 过滤属性(双引号)
		result = result
				.replaceAll(
						"(\\s+)([\\w|:]+)(\\s*)\\=(\\s*)(\"[^\"]*\")",
						"$1\4$2</font>$3\5=</font>$4\6$5</font>");
		// 过滤属性(单引号)
		result = result
				.replaceAll(
						"(\\s+)([\\w|:]+)(\\s*)\\=(\\s*)(\'[^\']*\')",
						"$1\4$2</font>$3\5=</font>$4\6$5</font>");
		// 指令字符串(双引号)
		//result = result.replaceAll("[^\6]\"[^\"]*\"[^<]", "\6$0</font>");
		// 指令字符串(双引号)
		//result = result.replaceAll("[^\6]\'[^\']*\'[^<]", "\6$0</font>");
		// 过滤dtd实体
		result = result.replaceAll("(&amp;#?[0-9|a-z|A-Z]+;)", "\7$1</font>");
		// 过滤空格
		result = result.replaceAll(" ", "&nbsp;");
		// 过滤缩进
		result = result.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		// 过滤Winodws换行
		result = result.replaceAll("\r\n", "\n");
		// 过滤换行
		result = result.replaceAll("\n", "<br/>\n");
		// 下面的替换把上面作的标记换成相应颜色
		result = result.replaceAll("\0", "</font>");
		result = result.replaceAll("\1", "<font color=\"" + dataColor + "\">");
		result = result.replaceAll("\2", "<font color=\"" + commentColor + "\">");
		result = result.replaceAll("\3", "<font color=\"" + tagColor + "\">");
		result = result.replaceAll("\4", "<font color=\"" + attributeColor + "\">");
		result = result.replaceAll("\5", "<font color=\"" + equalColor + "\">");
		result = result.replaceAll("\6", "<font color=\"" + stringColor + "\">");
		result = result.replaceAll("\7", "<font color=\"" + entityColor + "\">");
		if (textColor != null && textColor.length() > 0)
			return "<font color=\"" + textColor + "\">" + result + "</font>";
		return result;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getCommentColor() {
		return commentColor;
	}

	public void setCommentColor(String commentColor) {
		this.commentColor = commentColor;
	}

	public String getTagColor() {
		return tagColor;
	}

	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}

	public String getAttributeColor() {
		return attributeColor;
	}

	public void setAttributeColor(String attributeColor) {
		this.attributeColor = attributeColor;
	}

	public String getEqualColor() {
		return equalColor;
	}

	public void setEqualColor(String equalColor) {
		this.equalColor = equalColor;
	}

	public String getStringColor() {
		return stringColor;
	}

	public void setStringColor(String stringColor) {
		this.stringColor = stringColor;
	}

	public String getEntityColor() {
		return entityColor;
	}

	public void setEntityColor(String entityColor) {
		this.entityColor = entityColor;
	}

	public String getDataColor() {
		return dataColor;
	}

	public void setDataColor(String dataColor) {
		this.dataColor = dataColor;
	}

}
