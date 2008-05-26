package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.config.ResourceFilter;
import org.commontemplate.config.TemplateConfiguration;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionParser;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateParser;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.util.Assert;
import org.commontemplate.util.Location;
import org.commontemplate.util.scanner.ScanningException;

/**
 * 指令引擎 (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class TemplateEngine implements TemplateParser {

	private final ExpressionParser expressionParser;

	private final DirectiveTokenizer directiveTokenizer;

	private final DirectiveTranslator directiveTranslator;

	private final DirectiveReducer directiveReducer;

	private final ResourceFilter resourceFilter;

	public TemplateEngine(TemplateConfiguration config) {
		Assert.assertNotNull(config, "TemplateEngine.config.required");
		expressionParser = new ExpressionEngine(config);
		directiveTokenizer = new DirectiveTokenizer(config.getSyntax());
		directiveTranslator = new DirectiveTranslator(new DirectiveFactory(
				config.getSyntax(), config.getDirectiveHandlerProvider(),
				expressionParser, config.getTextFilter(), config.getRenderInterceptors()));
		directiveReducer = new DirectiveReducer();
		resourceFilter = config.getResourceFilter();
	}

	private final BlockDirective parseDirective(Reader templateProvider) throws IOException, ParsingException {
		try {
			List tokens = directiveTokenizer.split(templateProvider);
			List directives = directiveTranslator.translate(tokens);
			BlockDirective root = directiveReducer.reduce(directives);
			return root;
		} catch (ParsingException e) {
			throw e;
		} catch (ScanningException e) {
			throw new ParsingException(new Location(e.getPosition(), e.getPosition()), e);
		}
	}

	public final Expression parseExpression(String text) throws ParsingException {
		try {
			return expressionParser.parseExpression(text);
		} catch (ParsingException e) {
			e.setResource(new AnonymousResource(text));
			throw e;
		}
	}

	public final Template parseTemplate(Resource resource)
			throws ParsingException, IOException {
		try {
			return new TemplateImpl(getReader(resource), resource, parseDirective(getReader(resource)));
		} catch (ParsingException e) {
			e.setResource(resource);
			throw e;
		}
	}

	private final Reader getReader(Resource resource) throws IOException {
		Reader reader = resource.getReader();
		if (resourceFilter != null)
			reader = resourceFilter.filter(reader);
		return reader;
	}

	public final Template parseTemplate(String template) throws ParsingException {
		Assert.assertNotNull(template, "TemplateEngine.template.name.required");
		Resource resource = new AnonymousResource(template);
		try {
			return parseTemplate(resource);
		} catch (IOException e) { // 因为是字符串模板，一般不会出现IOException
			throw new RuntimeException(e);
		}
	}

}
