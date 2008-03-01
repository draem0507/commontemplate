package org.commontemplate.engine;

import java.io.Writer;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.config.Keywords;
import org.commontemplate.config.ResourceBundleProvider;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.core.Context;
import org.commontemplate.core.ContextFactory;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.Logger;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LocaleUtils;

/**
 * 上下文工厂实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class ContextFactoryImpl implements ContextFactory {
	
	private final GlobalContext globalContext;
	
	private final TemplateFactory templateFactory;
	
	private final TemplateNameFilter templateNameFilter;

	private final ResourceBundleProvider resourceBundleProvider;
	
	private final OutputFormatter defaultFormater;

	private final EventListener eventListener;
	
	private final Logger logger;
	
	private final boolean debugMode;

	private final Keywords keywords;

	ContextFactoryImpl(TemplateFactory templateFactory, 
			TemplateNameFilter templateNameFilter,
			ResourceBundleProvider resourceBundleProvider, 
			OutputFormatter defaultFormater, 
			EventListener eventListener, 
			Logger logger, 
			boolean debugMode, 
			Keywords keywords) {
		Assert.assertNotNull(templateFactory);
		Assert.assertNotNull(keywords);
		this.globalContext = new GlobalContextImpl(keywords);
		this.templateFactory = templateFactory;
		this.templateNameFilter = templateNameFilter;
		this.resourceBundleProvider = resourceBundleProvider;
		this.defaultFormater = defaultFormater;
		this.eventListener = eventListener;
		this.logger = logger;
		this.debugMode = debugMode;
		this.keywords = keywords;
	}

	public Context createContext(Writer out) {
		return createContext(out, Locale.getDefault(), TimeZone.getDefault());
	}

	public Context createContext(Writer out, Locale locale) {
		return createContext(out, locale, LocaleUtils.getDefaultTimeZone(locale));
	}
	
	public Context createContext(Writer out, Locale locale, TimeZone timeZone) {
		return new ContextImpl(globalContext, out, 
				(resourceBundleProvider == null ? null : resourceBundleProvider.getResourceBundle(locale)), locale, 
				timeZone, templateFactory, templateNameFilter, defaultFormater, eventListener, logger, debugMode, keywords);
	}
	
	public GlobalContext getGlobalContext() {
		return globalContext;
	}

}