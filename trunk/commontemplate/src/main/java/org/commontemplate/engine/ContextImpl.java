package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.commontemplate.config.Keywords;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.core.Context;
import org.commontemplate.core.DefinedException;
import org.commontemplate.core.Event;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.EventPublisher;
import org.commontemplate.core.Expression;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.LocalContextStack;
import org.commontemplate.core.Logger;
import org.commontemplate.core.MessageSource;
import org.commontemplate.core.NoSuchMessageException;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.core.TemplateStack;
import org.commontemplate.core.Resource;
import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableException;
import org.commontemplate.util.Assert;

/**
 * 上下文实现
 *
 * @author liangfei0201@163.com
 *
 */
final class ContextImpl extends Context {

	ContextImpl(GlobalContext globalContext, Writer out,
			ResourceBundle resourceBundle, Locale locale, TimeZone timeZone,
			TemplateFactory templateFactory, TemplateNameFilter templateNameFilter, OutputFormatter defaultFormater,
			EventListener eventListener, Logger logger, boolean debugMode, Keywords keywords) {
		Assert.assertNotNull(globalContext);
		Assert.assertNotNull(out);
		Assert.assertNotNull(templateFactory);
		Assert.assertNotNull(keywords);

		this.globalContext = globalContext;
		this.out = out;
		this.resourceBundle = resourceBundle;
		this.locale = locale;
		this.timeZone = timeZone;
		this.templateFactory = templateFactory;
		this.templateNameFilter = templateNameFilter;
		this.defaultFormater = defaultFormater;
		this.eventListener = eventListener;
		this.logger = logger;
		this.debugMode = debugMode;
		this.keywords = keywords;

		eventPublisher = new EventPublisherImpl(eventListener, this);
		templateNameStack = new TemplateStackImpl(eventPublisher, templateNameFilter);
		localContextStack = new LocalContextStackImpl(out, defaultFormater, eventPublisher, this, keywords);
		messageSource = new MessageSourceImpl(resourceBundle, locale);
	}

	private final TemplateNameFilter templateNameFilter;

	// 实现 Context -----------

	private final Keywords keywords;

	private final EventListener eventListener;

	private final OutputFormatter defaultFormater;

	private final ResourceBundle resourceBundle;

	public Context createContext() {
		return new ContextImpl(globalContext, out, resourceBundle, locale, timeZone, templateFactory, templateNameFilter, defaultFormater, eventListener, logger, debugMode, keywords);
	}

	private final GlobalContext globalContext;

	public GlobalContext getGlobalContext() {
		return globalContext;
	}

	private final Writer out;

	public Writer getOut() {
		return out;
	}

	private final Locale locale;

	public Locale getLocale() {
		return locale;
	}

	private final TimeZone timeZone;

	public TimeZone getTimeZone() {
		return timeZone;
	}

	private final boolean debugMode;

	public boolean isDebugMode() {
		return debugMode;
	}

	public void clear() {
		// 清理栈
		clearLocalContexts(); // 此清理将调用各级LocalContext的clear();
		clearTemplates();
	}

	// 代理 EventPublisher ----------

	private final EventPublisher eventPublisher;

	public void publishEvent(Event event) {
		eventPublisher.publishEvent(event);
	}

	// 代理 TemplateNameStack --------------

	private final TemplateStack templateNameStack;

	public void clearTemplates() {
		templateNameStack.clearTemplates();
	}

	public Template getCurrentTemplate() {
		return templateNameStack.getCurrentTemplate();
	}

	public Iterator getTemplateStackValues() {
		return templateNameStack.getTemplateStackValues();
	}

	public Template findTemplate(String name) {
		return templateNameStack.findTemplate(name);
	}

	public void popTemplate() {
		templateNameStack.popTemplate();
	}

	public void pushTemplate(Template template) {
		templateNameStack.pushTemplate(template);
	}

	public boolean containsTemplate(String name) {
		return templateNameStack.containsTemplate(name);
	}

	public String relateTemplateName(String name) {
		return templateNameStack.relateTemplateName(name);
	}

	// 代理 LocalContextStack -------

	private final LocalContextStack localContextStack;

	public void clearLocalContexts() {
		localContextStack.clearLocalContexts();
	}

	public LocalContext findLocalContext(String name) {
		return localContextStack.findLocalContext(name);
	}

	public LocalContext getCurrentLocalContext() {
		return localContextStack.getCurrentLocalContext();
	}

	public Iterator getLocalContextStackValues() {
		return localContextStack.getLocalContextStackValues();
	}

	public LocalContext getRootLocalContext() {
		return localContextStack.getRootLocalContext();
	}

	public void popLocalContext() {
		localContextStack.popLocalContext();
	}

	public void pushLocalContext() {
		localContextStack.pushLocalContext();
	}

	public void pushLocalContext(String name) {
		localContextStack.pushLocalContext(name);
	}

	public void pushLocalContext(Map variablesContainer) {
		localContextStack.pushLocalContext(variablesContainer);
	}

	public void pushLocalContext(String name,
			Map variablesContainer) {
		localContextStack.pushLocalContext(name, variablesContainer);
	}

	// 代理 MessageSource ---------

	private final MessageSource messageSource;

	public String getMessage(String key, Object[] args, String defaultValue) {
		return messageSource.getMessage(key, args, defaultValue);
	}

	public String getMessage(String key, Object[] args)
			throws NoSuchMessageException {
		return messageSource.getMessage(key, args);
	}

	public String getMessage(String key, String defaultValue) {
		return messageSource.getMessage(key, defaultValue);
	}

	public String getMessage(String key) throws NoSuchMessageException {
		return messageSource.getMessage(key);
	}
	// 代理 TemplateFactory -----------

	private final TemplateFactory templateFactory;

	private String getCurrentTemplateEncoding() {// 获取当前模板编码
		Template template = this.getCurrentTemplate();
		if (template != null)
			return template.getEncoding();
		return null;
	}

	public Template getTemplate(String name) throws IOException, ParsingException {
		String encoding = getCurrentTemplateEncoding();
		if (encoding != null)
			return templateFactory.getTemplate(relateTemplateName(name), encoding);
		else
			return templateFactory.getTemplate(relateTemplateName(name));
	}

	public Template getTemplate(String name, String encoding)
			throws IOException, ParsingException {
		return templateFactory.getTemplate(relateTemplateName(name), encoding);
	}

	public Resource loadResource(String name)
			throws IOException {
		String encoding = getCurrentTemplateEncoding();
		if (encoding != null)
			return templateFactory.getTemplate(relateTemplateName(name), encoding);
		else
			return templateFactory.loadResource(relateTemplateName(name));
	}

	public Resource loadResource(String name, String encoding)
			throws IOException {
		return templateFactory.loadResource(relateTemplateName(name), encoding);
	}

	public Expression parseExpression(String expression) throws ParsingException {
		return templateFactory.parseExpression(expression);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return templateFactory.parseTemplate(template);
	}

	public Template parseTemplate(Resource resource)
			throws ParsingException, IOException {
		return templateFactory.parseTemplate(resource);
	}

	// 代理 Logger ------

	private final Logger logger;

	public void debug(String msg, Throwable e) {
		if (logger != null)
			logger.debug(msg, e);
	}

	public void debug(String msg) {
		if (logger != null)
			logger.debug(msg);
	}

	public void info(String msg) {
		if (logger != null)
			logger.info(msg);
	}

	public void warn(String msg, Throwable e) {
		if (logger != null)
			logger.warn(msg, e);
	}

	public void warn(String msg) {
		if (logger != null)
			logger.warn(msg);
	}

	public void error(String msg, Throwable e) {
		if (logger != null)
			logger.error(msg, e);
	}

	public void error(String msg) {
		if (logger != null)
			logger.error(msg);
	}

	public boolean isDebugEnabled() {
		return logger != null && logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger != null && logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger != null && logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger != null && logger.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return logger != null && logger.isFatalEnabled();
	}

	// 代理 栈顶的LocalContext --------------

	public LocalContext getSuperLocalContext() {
		return getCurrentLocalContext().getSuperLocalContext();
	}

	public String getLocalContextName() {
		return getCurrentLocalContext().getLocalContextName();
	}

	// 代理 OutputController ------

	public void setOutputFilter(OutputFilter outputFilter) {
		getCurrentLocalContext().setOutputFilter(outputFilter);
	}

	public void removeOutputFilter() {
		getCurrentLocalContext().removeOutputFilter();
	}

	public void setGeneralOutputFormatter(OutputFormatter outputFormater) {
		getCurrentLocalContext().setGeneralOutputFormatter(outputFormater);
	}

	public void removeGeneralOutputFormatter() {
		getCurrentLocalContext().removeGeneralOutputFormatter();
	}

	public void setOutputFormatter(Class type, OutputFormatter outputFormatter) {
		getCurrentLocalContext().setOutputFormatter(type, outputFormatter);
	}

	public void removeOutputFormatter(Class type) {
		getCurrentLocalContext().removeOutputFormatter(type);
	}

	public void clearOutputFormatters() {
		getCurrentLocalContext().clearOutputFormatters();
	}

	public void output(Object model) throws IOException {
		getCurrentLocalContext().output(model);
	}

	public String format(Object model) {
		return getCurrentLocalContext().format(model);
	}

	public OutputFormatter getGeneralOutputFormatter() {
		return getCurrentLocalContext().getGeneralOutputFormatter();
	}

	public OutputFilter getOutputFilter() {
		return getCurrentLocalContext().getOutputFilter();
	}

	public OutputFormatter getOutputFormatter(Class type) {
		return getCurrentLocalContext().getOutputFormatter(type);
	}

	// 代理 VariableStorage ------------

	public boolean isDefinedVariable(String name) throws VariableException {
		return getCurrentLocalContext().isDefinedVariable(name);
	}

	public void defineVariable(String name) throws DefinedException, VariableException {
		getCurrentLocalContext().defineVariable(name);
	}

	public void defineVariable(String name, Object value) throws DefinedException, VariableException {
		getCurrentLocalContext().defineVariable(name, value);
	}

	public void defineReadonlyVariable(String name, Object value) throws DefinedException, VariableException {
		getCurrentLocalContext().defineReadonlyVariable(name, value);
	}

	public void defineVariableAlias(String alias, String name) throws VariableException{
		getCurrentLocalContext().defineVariableAlias(alias, name);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		getCurrentLocalContext().removeVariableAlias(alias);
	}

	public void assignVariable(String name, Object value) throws UndefinedException, VariableException {
		getCurrentLocalContext().assignVariable(name, value);
	}

	public Object lookupVariable(String name) throws UndefinedException, VariableException {
		return getCurrentLocalContext().lookupVariable(name);
	}

	public void removeVariable(String name) throws UndefinedException, VariableException {
		getCurrentLocalContext().removeVariable(name);
	}

	public void clearVariables() {
		getCurrentLocalContext().clearVariables();
	}

	public Map getDefinedVariables() {
		return getCurrentLocalContext().getDefinedVariables();
	}

	public void defineAllVariables(Map variables) throws DefinedException, VariableException {
		getCurrentLocalContext().defineAllVariables(variables);
	}

	public void lockVariables() {
		getCurrentLocalContext().lockVariables();
	}

	public void unlockVariables() {
		getCurrentLocalContext().unlockVariables();
	}

	// 代理 StatusStorage ----------

	public Object getStatus(String index) {
		return getCurrentLocalContext().getStatus(index);
	}

	public void setStatus(String index, Object value) {
		getCurrentLocalContext().setStatus(index, value);
	}

	public void removeStatus(String index) {
		getCurrentLocalContext().removeStatus(index);
	}

	public void clearStatus() {
		getCurrentLocalContext().clearStatus();
	}

	// 代理 ObjectStorage -------------

	public void clearObjects() {
		getCurrentLocalContext().clearObjects();
	}

	public Object lookupObject(String type, String name) {
		return getCurrentLocalContext().lookupObject(type, name);
	}

	public void putObject(String type, String name, Object value) {
		getCurrentLocalContext().putObject(type, name, value);
	}

	public void removeObject(String type, String name) {
		getCurrentLocalContext().removeObject(type, name);
	}

	public Object lookupObject(String name) {
		return getCurrentLocalContext().lookupObject(name);
	}

	public void putObject(String name, Object value) {
		getCurrentLocalContext().putObject(name, value);
	}

	public void removeObject(String name) {
		getCurrentLocalContext().removeObject(name);
	}

	private boolean step;

	public boolean isStep() {
		return step;
	}

	public void setStep(boolean step) {
		this.step = step;
	}

	private boolean over;

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

}
