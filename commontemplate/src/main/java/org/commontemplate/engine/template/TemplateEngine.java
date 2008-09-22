package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.ResourceFilter;
import org.commontemplate.config.Syntax;
import org.commontemplate.config.TemplateConfiguration;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.TemplateParser;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
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

	private final ExpressionEngine expressionParser;

	private final DirectiveTokenizer directiveTokenizer;

	private final DirectiveTranslator directiveTranslator;

	private final DirectiveReducer directiveReducer;

	private final ResourceFilter resourceFilter;

	private final TemplateFactory elementFactory;

	private final Syntax syntax;

	private final List renderInterceptors;

	private final DirectiveHandlerProvider directiveHandlerProvider;

	public TemplateEngine(TemplateConfiguration config) {
		Assert.assertNotNull(config, "TemplateEngine.config.required");
		directiveHandlerProvider = config.getDirectiveHandlerProvider();
		syntax = config.getSyntax();
		renderInterceptors = config.getRenderInterceptors();
		expressionParser = new ExpressionEngine(config);
		directiveTokenizer = new DirectiveTokenizer(config.getSyntax());
		directiveTranslator = new DirectiveTranslator(new DirectiveProvider(
				syntax, directiveHandlerProvider,
				expressionParser, config.getTextFilter(), renderInterceptors));
		directiveReducer = new DirectiveReducer();
		resourceFilter = config.getResourceFilter();
		elementFactory = new TemplateFactoryImpl(directiveHandlerProvider, renderInterceptors);
	}

	private final RootBlockDirectiveImpl parseDirective(Reader templateProvider) throws IOException, ParsingException {
		try {
			List tokens = directiveTokenizer.split(templateProvider);
			List directives = directiveTranslator.translate(tokens);
			RootBlockDirectiveImpl root = directiveReducer.reduce(directives);
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
			e.setResource(new ResourceImpl(text));
			throw e;
		}
	}

	public final Template parseTemplate(Resource resource)
			throws ParsingException, IOException {
		try {
			return new TemplateImpl(getReader(resource), resource, parseDirective(getReader(resource)), renderInterceptors);
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
		Resource resource = new ResourceImpl(template);
		try {
			return parseTemplate(resource);
		} catch (IOException e) { // 因为是字符串模板，一般不会出现IOException
			throw new RuntimeException(e);
		}
	}

	public TemplateBudiler createTemplateBudiler() {
		return new TemplateBudilerImpl(syntax, directiveHandlerProvider, renderInterceptors);
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOprand, Expression rightOprand) {
		return expressionParser.createBinaryOperator(operatorName, leftOprand,
				rightOprand);
	}

	public Constant createConstant(Object constantValue) {
		return expressionParser.createConstant(constantValue);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression oprand) {
		return expressionParser.createUnaryOperator(operatorName, oprand);
	}

	public Variable createVariable(String variableName) {
		return expressionParser.createVariable(variableName);
	}

	public ExpressionBuilder createExpressionBuilder() {
		return expressionParser.createExpressionBuilder();
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		return elementFactory.createBlockDirective(name, expression, elements);
	}

	public Comment createComment(String comment) {
		return elementFactory.createComment(comment);
	}

	public Directive createDirective(String name, Expression expression) {
		return elementFactory.createDirective(name, expression);
	}

	public Text createText(String text) {
		return elementFactory.createText(text);
	}

	public Template createTemplate(String name, List elements) {
		return elementFactory.createTemplate(name, elements);
	}

}
