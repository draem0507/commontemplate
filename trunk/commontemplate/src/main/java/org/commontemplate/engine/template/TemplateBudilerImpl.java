package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.Syntax;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;

/**
 * 模板构建器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class TemplateBudilerImpl implements TemplateBudiler { // TODO 未完成

	private final Syntax syntax;

	private final DirectiveHandlerProvider directiveHandlerProvider;

	private final List elementInterceptors;

	TemplateBudilerImpl(Syntax syntax, DirectiveHandlerProvider directiveHandlerProvider, List elementInterceptors) {
		this.syntax = syntax;
		this.directiveHandlerProvider = directiveHandlerProvider;
		this.elementInterceptors = elementInterceptors;
	}

	private BlockDirectiveSupport blockDirective = new RootBlockDirectiveImpl();

	private List elements = new ArrayList();

	public void addComment(String comment) {
		elements.add(new CommentImpl(String.valueOf(syntax.getBlockComment()), null, comment, comment));
	}

	public void addDirective(String directiveName, Expression expression) {
		elements.add(new DirectiveImpl(directiveName, null, expression, directiveHandlerProvider.getDirectiveHandler(directiveName),
				String.valueOf(syntax.getDirectiveLeader())
				+ directiveName + String.valueOf(syntax.getExpressionBegin())
				+ expression.toString() + String.valueOf(syntax.getExpressionEnd()), elementInterceptors));
	}

	public void addText(String text) {
		elements.add(new TextImpl(String.valueOf(syntax.getNoParse()), null, text, elementInterceptors));
	}

	public Template getTemplate() {
		return template;
	}

	public void endBlockDirective() {
		// TODO Auto-generated method stub

	}

	public void beginBlockDirective(String directiveName, Expression expression) {
		elements.add(new BlockDirectiveImpl(directiveName, null, expression, (BlockDirectiveHandler)directiveHandlerProvider.getDirectiveHandler(directiveName),
				String.valueOf(syntax.getDirectiveLeader())
				+ directiveName + String.valueOf(syntax.getExpressionBegin())
				+ expression.toString() + String.valueOf(syntax.getExpressionEnd()), syntax.getEndDirective(), elementInterceptors));
	}

	private String templateName;

	public void beginTemplate(String templateName) {
		this.templateName = templateName;
	}

	private Template template;

	public void endTemplate() {
		try {
			template = new TemplateImpl(new StringReader(""), new ResourceImpl("", templateName), blockDirective);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
