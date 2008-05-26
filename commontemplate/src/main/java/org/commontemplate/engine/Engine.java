package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Context;
import org.commontemplate.core.ContextFactory;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Factory;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.core.Resource;
import org.commontemplate.engine.template.TemplateEngine;
import org.commontemplate.util.Assert;

/**
 * 模板及上下文引擎 (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class Engine implements Factory {

	private final TemplateFactory templateFactory;

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
		this.templateFactory = new TemplateFactoryImpl(new TemplateEngine(config),
				config.getResourceLoader(), config.getTemplateCache(),
				config.getReloadController(), config.getResourceComparator());
		// 创建上下文工厂
		this.contextFactory = new ContextFactoryImpl(
				templateFactory, config.getTemplateNameFilter(), config.getDefaultOutputFormatter(),
				config.getEventListener(), config.isDebug(),
				config.getKeywords());
	}

	public final Template getTemplate(String name) throws IOException, ParsingException {
		return templateFactory.getTemplate(name);
	}

	public final Template getTemplate(String name, String encoding)
			throws IOException, ParsingException {
		return templateFactory.getTemplate(name, encoding);
	}

	public final Resource loadResource(String name, String encoding)
			throws IOException {
		return templateFactory.loadResource(name, encoding);
	}

	public final Resource loadResource(String name) throws IOException {
		return templateFactory.loadResource(name);
	}

	public final Expression parseExpression(String expression) throws ParsingException {
		return templateFactory.parseExpression(expression);
	}

	public final Template parseTemplate(String template) throws ParsingException {
		return templateFactory.parseTemplate(template);
	}

	public final Template parseTemplate(Resource resource)
			throws ParsingException, IOException {
		return templateFactory.parseTemplate(resource);
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
