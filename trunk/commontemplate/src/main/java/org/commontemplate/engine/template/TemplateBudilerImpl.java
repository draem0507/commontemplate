package org.commontemplate.engine.template;

import java.io.IOException;
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
final class TemplateBudilerImpl implements TemplateBudiler {

	private final String templateName;

	private final Syntax syntax;

	private final DirectiveHandlerProvider directiveHandlerProvider;

	private final List renderInterceptors;

	private final DirectiveReducer directiveReducer = new DirectiveReducer();

	TemplateBudilerImpl(String templateName, Syntax syntax, DirectiveHandlerProvider directiveHandlerProvider, List renderInterceptors) {
		this.templateName = templateName;
		this.syntax = syntax;
		this.directiveHandlerProvider = directiveHandlerProvider;
		this.renderInterceptors = renderInterceptors;
	}

	private List elements = new ArrayList();

	public void addComment(String comment) {
		elements.add(new CommentImpl(String.valueOf(syntax.getBlockComment()), null, comment, comment, renderInterceptors));
	}

	public void addDirective(String directiveName, Expression expression) {
		elements.add(new DirectiveImpl(directiveName, null, expression, directiveHandlerProvider.getDirectiveHandler(directiveName),
				String.valueOf(syntax.getDirectiveLeader())
				+ directiveName + String.valueOf(syntax.getExpressionBegin())
				+ expression.toString() + String.valueOf(syntax.getExpressionEnd()), renderInterceptors));
	}

	public void addText(String text) {
		elements.add(new TextImpl(String.valueOf(syntax.getNoParse()), null, text, renderInterceptors));
	}

	public Template getTemplate() {
		RootBlockDirectiveImpl rootBlockDirective = directiveReducer.reduce(elements);
		try {
			return new TemplateImpl(new ResourceImpl("", templateName), rootBlockDirective, renderInterceptors);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void beginBlockDirective(String directiveName, Expression expression) {
		elements.add(new BlockDirectiveImpl(directiveName, null, expression, (BlockDirectiveHandler)directiveHandlerProvider.getDirectiveHandler(directiveName),
				String.valueOf(syntax.getDirectiveLeader())
				+ directiveName + String.valueOf(syntax.getExpressionBegin())
				+ expression.toString() + String.valueOf(syntax.getExpressionEnd()), syntax.getEndDirective(), renderInterceptors));
	}

	public void endBlockDirective() {
		elements.add(EndDirective.END_DIRECTIVE);
	}

	public void endBlockDirective(String directiveName) {
		elements.add(new EndDirective(directiveName));
	}

}
