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
import org.commontemplate.core.ElementFactory;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Template;
import org.commontemplate.core.Text;
import org.commontemplate.util.Assert;

final class ElementFactoryImpl implements ElementFactory {

	private final DirectiveHandlerProvider directiveHandlerProvider;

	private final List elementInterceptors;

	public ElementFactoryImpl(DirectiveHandlerProvider directiveHandlerProvider, List elementInterceptors) {
		this.directiveHandlerProvider = directiveHandlerProvider;
		this.elementInterceptors = elementInterceptors;
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		DirectiveHandler handler = directiveHandlerProvider.getDirectiveHandler(name);
		Assert.assertTrue(handler instanceof BlockDirectiveHandler);
		if (handler instanceof MiddleBlockDirectiveHandler)
			return new MiddleBlockDirectiveImpl(name, null, expression, (MiddleBlockDirectiveHandler)handler, null, null, elementInterceptors);
		return new BlockDirectiveImpl(name, null, expression, (BlockDirectiveHandler)handler, null, null, elementInterceptors);
	}

	public Directive createDirective(String name, Expression expression) {
		DirectiveHandler handler = directiveHandlerProvider.getDirectiveHandler(name);
		return new DirectiveImpl(name, null, expression, (DirectiveHandler)handler, null, elementInterceptors);
	}

	public Comment createComment(String comment) {
		return new CommentImpl(comment, null, comment, comment);
	}

	public Text createText(String text) {
		return new TextImpl(text, null, text, elementInterceptors);
	}

	public Template createTemplate(String name, List elements) {
		try {
			return new TemplateImpl(null, null, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
