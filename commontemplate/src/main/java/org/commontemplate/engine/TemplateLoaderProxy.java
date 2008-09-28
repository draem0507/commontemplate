package org.commontemplate.engine;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;

class TemplateLoaderProxy implements TemplateLoader {

	private final TemplateLoader templateLoader;

	private final Context context;

	public TemplateLoaderProxy(TemplateLoader templateLoader, Context context) {
		this.templateLoader = templateLoader;
		this.context = context;
	}

	private String getCurrentTemplateEncoding() {// 获取当前模板编码
		Template template = context.getCurrentTemplate();
		if (template != null)
			return template.getEncoding();
		return null;
	}

	public Template getTemplate(String name) throws IOException, ParsingException {
		String encoding = getCurrentTemplateEncoding();
		if (encoding != null)
			return templateLoader.getTemplate(context.relateTemplateName(name), encoding);
		else
			return templateLoader.getTemplate(context.relateTemplateName(name));
	}

	public Template getTemplate(String name, String encoding)
			throws IOException, ParsingException {
		return templateLoader.getTemplate(context.relateTemplateName(name), encoding);
	}

	public Template getTemplate(String name, Locale locale) throws IOException,
			ParsingException {
		return templateLoader.getTemplate(context.relateTemplateName(name), locale);
	}

	public Template getTemplate(String name, Locale locale, String encoding)
			throws IOException, ParsingException {
		return templateLoader.getTemplate(context.relateTemplateName(name), locale, encoding);
	}

	public Resource getResource(String name)
			throws IOException {
		String encoding = getCurrentTemplateEncoding();
		if (encoding != null)
			return templateLoader.getResource(context.relateTemplateName(name), encoding);
		else
			return templateLoader.getResource(context.relateTemplateName(name));
	}

	public Resource getResource(String name, String encoding)
			throws IOException {
		return templateLoader.getResource(context.relateTemplateName(name), encoding);
	}

	public Resource getResource(String name, Locale locale) throws IOException {
		String encoding = getCurrentTemplateEncoding();
		if (encoding != null)
			return templateLoader.getResource(context.relateTemplateName(name), locale, encoding);
		else
			return templateLoader.getResource(context.relateTemplateName(name), locale);
	}

	public Resource getResource(String name, Locale locale, String encoding)
			throws IOException {
		return templateLoader.getResource(context.relateTemplateName(name), locale, encoding);
	}

	public Expression parseExpression(String expression) throws ParsingException {
		return templateLoader.parseExpression(expression);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return templateLoader.parseTemplate(template);
	}

	public Template parseTemplate(Resource resource)
			throws ParsingException, IOException {
		return templateLoader.parseTemplate(resource);
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		return templateLoader.createBlockDirective(name, expression, elements);
	}

	public Comment createComment(String comment) {
		return templateLoader.createComment(comment);
	}

	public Directive createDirective(String name, Expression expression) {
		return templateLoader.createDirective(name, expression);
	}

	public Text createText(String text) {
		return templateLoader.createText(text);
	}

	public ExpressionBuilder createExpressionBuilder() {
		return templateLoader.createExpressionBuilder();
	}

	public TemplateBudiler createTemplateBudiler() {
		return templateLoader.createTemplateBudiler();
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOprand, Expression rightOprand) {
		return templateLoader.createBinaryOperator(operatorName, leftOprand,
				rightOprand);
	}

	public Constant createConstant(Object constantValue) {
		return templateLoader.createConstant(constantValue);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression oprand) {
		return templateLoader.createUnaryOperator(operatorName, oprand);
	}

	public Variable createVariable(String variableName) {
		return templateLoader.createVariable(variableName);
	}

	public Template createTemplate(String name, List elements) {
		return templateLoader.createTemplate(name, elements);
	}

}
