package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

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
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
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
public class TemplateEngine implements TemplateParser {

	private final TemplateParser templateParser;

	public TemplateEngine(TemplateConfiguration config) {
		Assert.assertNotNull(config, "TemplateEngine.config.required");
		ExpressionParser expressionParser = new ExpressionEngine(config);
		templateParser = new TemplateParserImpl(expressionParser,
				config.getDirectiveHandlerProvider(), config.getSyntax(),
				config.getRenderInterceptors(), config.getTextFilter(), config.getResourceFilter());
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOperand, Expression rightOperand) {
		return templateParser.createBinaryOperator(operatorName, leftOperand,
				rightOperand);
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		return templateParser.createBlockDirective(name, expression, elements);
	}

	public Comment createComment(String comment) {
		return templateParser.createComment(comment);
	}

	public Constant createConstant(Object constantValue) {
		return templateParser.createConstant(constantValue);
	}

	public Directive createDirective(String name, Expression expression) {
		return templateParser.createDirective(name, expression);
	}

	public ExpressionBuilder createExpressionBuilder() {
		return templateParser.createExpressionBuilder();
	}

	public Template createTemplate(String name, List elements) {
		return templateParser.createTemplate(name, elements);
	}

	public TemplateBudiler createTemplateBudiler() {
		return templateParser.createTemplateBudiler();
	}

	public Text createText(String text) {
		return templateParser.createText(text);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression operand) {
		return templateParser.createUnaryOperator(operatorName, operand);
	}

	public Variable createVariable(String variableName) {
		return templateParser.createVariable(variableName);
	}

	public Expression parseExpression(String expression)
			throws ParsingException {
		return templateParser.parseExpression(expression);
	}

	public Template parseTemplate(Resource resource) throws ParsingException,
			IOException {
		return templateParser.parseTemplate(resource);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return templateParser.parseTemplate(template);
	}

}
