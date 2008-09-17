package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Context;
import org.commontemplate.core.ContextFactory;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.Factory;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.engine.template.TemplateEngine;
import org.commontemplate.util.Assert;

/**
 * 模板及上下文引擎 (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class Engine implements Factory {

	private final TemplateLoader templateLoader;

	private final ContextFactory contextFactory;

	/**
	 * 通过配置创建引擎
	 *
	 * @param config
	 *            引擎所需的配置信息
	 */
	public Engine(Configuration config) {
		Assert.assertNotNull(config, "Engine.config.required");
		// 创建模板工厂
		this.templateLoader = new TemplateLoaderImpl(new TemplateEngine(config),
				config.getResourceLoader(), config.getTemplateCache(),
				config.getReloadController(), config.getResourceComparator());
		// 创建上下文工厂
		this.contextFactory = new ContextFactoryImpl(
				this.templateLoader, config.getContextInitializer(),
				config.getTemplateNameFilter(), config.getDefaultOutputFormatter(),
				config.getEventListener(), config.isDebug(),
				config.getKeywords());
	}

	public final Template getTemplate(String name) throws IOException, ParsingException {
		return templateLoader.getTemplate(name);
	}

	public final Template getTemplate(String name, String encoding)
			throws IOException, ParsingException {
		return templateLoader.getTemplate(name, encoding);
	}

	public final Resource loadResource(String name, String encoding)
			throws IOException {
		return templateLoader.loadResource(name, encoding);
	}

	public final Resource loadResource(String name) throws IOException {
		return templateLoader.loadResource(name);
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

	public final Expression parseExpression(String expression) throws ParsingException {
		return templateLoader.parseExpression(expression);
	}

	public final Template parseTemplate(String template) throws ParsingException {
		return templateLoader.parseTemplate(template);
	}

	public final Template parseTemplate(Resource resource)
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

	public ExpressionBuilder getExpressionBuilder() {
		return templateLoader.getExpressionBuilder();
	}

	public TemplateBudiler getTemplateBudiler(String templateName) {
		return templateLoader.getTemplateBudiler(templateName);
	}

	public Template createTemplate(String name, List elements) {
		return templateLoader.createTemplate(name, elements);
	}

	public final Context createContext(Writer out) {
		return contextFactory.createContext(out);
	}

	public final Context createContext(Writer out, Locale locale) {
		return contextFactory.createContext(out, locale);
	}

	public final Context createContext(Writer out, Locale locale, TimeZone timeZone) {
		return contextFactory.createContext(out, locale, timeZone);
	}

	public final GlobalContext getGlobalContext() {
		return contextFactory.getGlobalContext();
	}

}
