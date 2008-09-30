package org.commontemplate.standard.coat;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;

import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.SourceFilter;
import org.commontemplate.config.Syntax;
import org.commontemplate.standard.syntax.SyntaxSettings;

/**
 * HTML属性语法外套实现.<br>
 * 将HTML标签的特殊属性转换为指令.<br>
 * 如:<br>
 * <pre>
 * &lt;table ct:if="users != null && users.size &gt; 0" border="1"&gt;
 *     &lt;tr ct:for="user : users"&gt;
 *         &lt;td&gt;&lt;span ct:out="for.index + 1"&gt;1&lt;/span&gt;&lt;/td&gt;
 *         &lt;td&gt;&lt;span ct:out="user.name"&gt;james&lt;/span&gt;&lt;/td&gt;
 *         &lt;td&gt;&lt;span ct:out="user.coins"&gt;2.00&lt;/span&gt;&lt;/td&gt;
 *     &lt;/tr&gt;
 * &lt;/table&gt;
 * </pre>
 *
 * @author GuiLeen
 * @author liangfei0201@163.com
 */
public class AttributeSyntaxCoatFilter implements SourceFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private boolean attributeSyntaxCoatAvailable = true;

	public boolean isAttributeSyntaxCoatAvailable() {
		return attributeSyntaxCoatAvailable;
	}

	public void setAttributeSyntaxCoatAvailable(boolean attributeSyntaxCoatAvailable) {
		this.attributeSyntaxCoatAvailable = attributeSyntaxCoatAvailable;
	}

	public static final String DEFAULT_NAMESPACE = "ct";

	private String namespace = DEFAULT_NAMESPACE;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		if (namespace != null && namespace.length() > 0)
			this.namespace = namespace;
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

	private DirectiveHandlerProvider directiveHandlerProvider;

	public DirectiveHandlerProvider getDirectiveHandlerProvider() {
		return directiveHandlerProvider;
	}

	public void setDirectiveHandlerProvider(
			DirectiveHandlerProvider directiveHandlerProvider) {
		this.directiveHandlerProvider = directiveHandlerProvider;
	}

	public Reader filter(Reader reader) throws IOException {
		return new StringReader(new JerichoFilter(namespace, syntax, directiveHandlerProvider).filter(reader));
	}

}
