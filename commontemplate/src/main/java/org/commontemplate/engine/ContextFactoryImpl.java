package org.commontemplate.engine;

import java.io.Writer;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.config.ContextInitializer;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.core.Context;
import org.commontemplate.core.ContextFactory;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LocaleUtils;

/**
 * 上下文工厂实现
 *
 * @author liangfei0201@163.com
 *
 */
final class ContextFactoryImpl implements ContextFactory {

	private final ContextInitializer contextInitializer;

	private final GlobalContext globalContext;

	private final TemplateLoader templateLoader;

	private final TemplateNameFilter templateNameFilter;

	private final OutputFormatter defaultFormater;

	private final EventListener eventListener;

	private final boolean debugMode;

	private final Keywords keywords;

	ContextFactoryImpl(TemplateLoader templateLoader,
			ContextInitializer contextInitializer,
			TemplateNameFilter templateNameFilter,
			OutputFormatter defaultFormater,
			EventListener eventListener,
			boolean debugMode,
			Keywords keywords) {
		Assert.assertNotNull(templateLoader);
		Assert.assertNotNull(keywords);
		this.globalContext = new GlobalContextImpl(keywords);
		this.contextInitializer = contextInitializer;
		this.templateLoader = templateLoader;
		this.templateNameFilter = templateNameFilter;
		this.defaultFormater = defaultFormater;
		this.eventListener = eventListener;
		this.debugMode = debugMode;
		this.keywords = keywords;
	}

	public GlobalContext getGlobalContext() {
		return globalContext;
	}

	public Context createContext(Writer out) {
		return createContext(out, Locale.getDefault(), TimeZone.getDefault());
	}

	public Context createContext(Writer out, Locale locale) {
		return createContext(out, locale, LocaleUtils.getDefaultTimeZone(locale));
	}

	public Context createContext(Writer out, Locale locale, TimeZone timeZone) {
		Context context = new ContextImpl(out, locale,
				timeZone, debugMode, templateLoader, this, templateNameFilter, defaultFormater, eventListener, keywords);
		if (contextInitializer != null)
			contextInitializer.initialize(context);
		return context;
	}

}