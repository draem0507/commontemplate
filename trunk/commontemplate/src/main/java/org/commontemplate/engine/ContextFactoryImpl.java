package org.commontemplate.engine;

import java.io.Writer;
import java.util.Locale;
import java.util.Map;

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

	private final boolean debug;

	private final Keywords keywords;

	private final Map scopeHandlers;

	ContextFactoryImpl(TemplateLoader templateLoader,
			ContextInitializer contextInitializer,
			TemplateNameFilter templateNameFilter,
			OutputFormatter defaultFormater,
			EventListener eventListener,
			Keywords keywords,
			Map scopeHandlers,
			boolean debug) {
		Assert.assertNotNull(templateLoader);
		Assert.assertNotNull(keywords);
		this.globalContext = new GlobalContextImpl(keywords);
		this.contextInitializer = contextInitializer;
		this.templateLoader = templateLoader;
		this.templateNameFilter = templateNameFilter;
		this.defaultFormater = defaultFormater;
		this.eventListener = eventListener;
		this.debug = debug;
		this.keywords = keywords;
		this.scopeHandlers = scopeHandlers;
	}

	public GlobalContext getGlobalContext() {
		return globalContext;
	}

	public Context createContext(Writer out) {
		Context context = new ContextImpl(out, templateLoader, this, templateNameFilter, defaultFormater, eventListener, keywords, scopeHandlers);
		context.setLocale(Locale.getDefault());
		context.setDebug(debug);
		if (contextInitializer != null)
			contextInitializer.initialize(context);
		return context;
	}

}