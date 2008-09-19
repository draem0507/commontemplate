package org.commontemplate.standard.coat;

import java.io.IOException;
import java.io.Reader;

import org.commontemplate.config.Syntax;
import org.commontemplate.standard.filter.StringResourceFilter;
import org.commontemplate.standard.syntax.SyntaxSettings;

/**
 * HTML标签语法外套实现.<br>
 * 将HTML特殊标签转换为指令.<br>
 * 如:<br>
 * <pre>
 * &lt;ct:if param="user != null"&gt;
 * &lt;/ct:if&gt;
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class TagSyntaxCoatFilter extends StringResourceFilter {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_NAMESPACE = "ct";

	private String namespace = DEFAULT_NAMESPACE;

	private boolean tagSyntaxCoatAvailable;

	public boolean isTagSyntaxCoatAvailable() {
		return tagSyntaxCoatAvailable;
	}

	public void setTagSyntaxCoatAvailable(boolean tagSyntaxCoatAvailable) {
		this.tagSyntaxCoatAvailable = tagSyntaxCoatAvailable;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		if (namespace != null && namespace.length() > 0)
			this.namespace = namespace;
	}

	public static final String DEFAULT_EXPRESSION_ATTRIBUTE_NAME = "param";

	private String expressionAttributeName = DEFAULT_EXPRESSION_ATTRIBUTE_NAME;

	public String getExpressionAttributeName() {
		return expressionAttributeName;
	}

	public void setExpressionAttributeName(String expressionAttributeName) {
		if (expressionAttributeName != null && expressionAttributeName.length() > 0)
			this.expressionAttributeName = expressionAttributeName;
	}

	private Syntax syntax = Syntax.DEFAULT;

	public Syntax getSyntax() {
		return syntax;
	}

	public void setSyntax(Syntax syntax) {
		if (syntax != null)
			this.syntax = syntax;
	}

	public void setSyntaxSettings(SyntaxSettings syntaxSettings) {
		if (syntaxSettings != null)
			this.syntax = syntaxSettings.toSyntax();
	}

	public Reader filter(Reader reader) throws IOException {
		if (! tagSyntaxCoatAvailable)
			return reader;
		return super.filter(reader);
	}

	public String filter(String text) {
		// 替换开始标签为指令
		String directiveRegex = "\\<" + namespace
				+ "\\s*\\:\\s*([0-9|_|A-Z|a-z]+)\\s+" + expressionAttributeName
				+ "\\s*\\=\\s*\\\"([^\\\"]+)\\\"\\s*\\/?\\>";
		String directiveReplacement = escapeRegexGroup(syntax.getDirectiveLeader()) + "$1"
				+ escapeRegexGroup(syntax.getExpressionBegin())+"$2"
				+ escapeRegexGroup(syntax.getExpressionEnd());
		text = text.replaceAll(directiveRegex, directiveReplacement);

		// 替换结束标签为结束指令
		String endRegex = "\\<\\/\\s*" + namespace
		+ "\\s*\\:\\s*([0-9|_|A-Z|a-z]+)\\s*\\>";
		String endReplacement = escapeRegexGroup(syntax.getDirectiveLeader())
				+ syntax.getEndDirectiveName()
				+ escapeRegexGroup(syntax.getExpressionBegin()) + "$1"
				+ escapeRegexGroup(syntax.getExpressionEnd());
		text = text.replaceAll(endRegex, endReplacement);

		// 返回替换后的结果
		return text;
	}

	// 转义正则表达式组引用符
	private String escapeRegexGroup(char sign) {
		if (sign == '$') // 正则表达式组引用符
			return "\\" + sign;
		return String.valueOf(sign);
	}

}
