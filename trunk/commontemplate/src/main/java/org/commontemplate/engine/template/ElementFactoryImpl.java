package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.MiddleBlockDirectiveHandler;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Directive;
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Template;
import org.commontemplate.core.Text;
import org.commontemplate.util.Assert;

final class ElementFactoryImpl implements TemplateFactory {

	private final DirectiveHandlerProvider directiveHandlerProvider;

	private final List renderInterceptors;

	public ElementFactoryImpl(DirectiveHandlerProvider directiveHandlerProvider, List renderInterceptors) {
		this.directiveHandlerProvider = directiveHandlerProvider;
		this.renderInterceptors = renderInterceptors;
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		DirectiveHandler handler = directiveHandlerProvider.getDirectiveHandler(name);
		Assert.assertTrue(handler instanceof BlockDirectiveHandler);
		if (handler instanceof MiddleBlockDirectiveHandler)
			return new MiddleBlockDirectiveImpl(name, null, expression, (MiddleBlockDirectiveHandler)handler, null, null, renderInterceptors);
		return new BlockDirectiveImpl(name, null, expression, (BlockDirectiveHandler)handler, null, null, renderInterceptors);
	}

	public Directive createDirective(String name, Expression expression) {
		DirectiveHandler handler = directiveHandlerProvider.getDirectiveHandler(name);
		return new DirectiveImpl(name, null, expression, (DirectiveHandler)handler, null, renderInterceptors);
	}

	public Comment createComment(String comment) {
		return new CommentImpl("*", null, comment, comment, renderInterceptors);
	}

	public Text createText(String text) {
		return new TextImpl(text, null, text, renderInterceptors);
	}

	public Template createTemplate(String name, List elements) {
		try {
			return new TemplateImpl(null, null, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
