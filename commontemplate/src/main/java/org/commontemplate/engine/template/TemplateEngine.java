package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.commontemplate.config.TemplateConfiguration;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.ExpressionParser;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Source;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.core.TemplateParser;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.util.Assert;

/**
 * 指令引擎 (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateEngine implements TemplateLoader {

	private final TemplateLoader templateLoader;

	public TemplateEngine(TemplateConfiguration config) {
		Assert.assertNotNull(config, "TemplateEngine.config.required");
		ExpressionParser expressionParser = new ExpressionEngine(config);
		TemplateParser templateParser = new TemplateParserImpl(expressionParser,
				config.getDirectiveHandlerProvider(), config.getSyntax(),
				config.getRenderInterceptors(), config.getTextFilter(), config.getSourceFilter());
		templateLoader = new TemplateLoaderImpl(templateParser, config.getSourceLoader(),
				config.getTemplateCache(), config.getTemplatePersistentCache(),
				config.getReloadController(), config.getSourceComparator());
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOperand, Expression rightOperand) {
		return templateLoader.createBinaryOperator(operatorName, leftOperand,
				rightOperand);
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		return templateLoader.createBlockDirective(name, expression, elements);
	}

	public Comment createComment(String comment) {
		return templateLoader.createComment(comment);
	}

	public Constant createConstant(Object constantValue) {
		return templateLoader.createConstant(constantValue);
	}

	public Directive createDirective(String name, Expression expression) {
		return templateLoader.createDirective(name, expression);
	}

	public ExpressionBuilder createExpressionBuilder() {
		return templateLoader.createExpressionBuilder();
	}

	public Template createTemplate(String name, List elements) {
		return templateLoader.createTemplate(name, elements);
	}

	public TemplateBudiler createTemplateBudiler() {
		return templateLoader.createTemplateBudiler();
	}

	public Text createText(String text) {
		return templateLoader.createText(text);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression operand) {
		return templateLoader.createUnaryOperator(operatorName, operand);
	}

	public Variable createVariable(String variableName) {
		return templateLoader.createVariable(variableName);
	}

	public Source getSource(String name, Locale locale, String encoding)
			throws IOException {
		return templateLoader.getSource(name, locale, encoding);
	}

	public Source getSource(String name, Locale locale) throws IOException {
		return templateLoader.getSource(name, locale);
	}

	public Source getSource(String name, String encoding) throws IOException {
		return templateLoader.getSource(name, encoding);
	}

	public Source getSource(String name) throws IOException {
		return templateLoader.getSource(name);
	}

	public Template getTemplate(String name, Locale locale, String encoding)
			throws IOException, ParsingException {
		return templateLoader.getTemplate(name, locale, encoding);
	}

	public Template getTemplate(String name, Locale locale) throws IOException,
			ParsingException {
		return templateLoader.getTemplate(name, locale);
	}

	public Template getTemplate(String name, String encoding)
			throws IOException, ParsingException {
		return templateLoader.getTemplate(name, encoding);
	}

	public Template getTemplate(String name) throws IOException,
			ParsingException {
		return templateLoader.getTemplate(name);
	}

	public Expression parseExpression(String expression)
			throws ParsingException {
		return templateLoader.parseExpression(expression);
	}

	public Template parseTemplate(Source resource) throws ParsingException,
			IOException {
		return templateLoader.parseTemplate(resource);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return templateLoader.parseTemplate(template);
	}

}
