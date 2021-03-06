package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.commontemplate.config.Keywords;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.core.Context;
import org.commontemplate.core.ContextFactory;
import org.commontemplate.core.Element;
import org.commontemplate.core.ElementStack;
import org.commontemplate.core.Event;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.EventPublisher;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.LocalContextStack;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.core.TemplateStack;
import org.commontemplate.core.VariableException;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LocaleUtils;

/**
 * 上下文实现
 *
 * @author liangfei0201@163.com
 *
 */
final class ContextImpl extends Context {

	private static final long serialVersionUID = 1L;

	ContextImpl(Writer out, TemplateLoader templateLoader, ContextFactory contextFactory, TemplateNameFilter templateNameFilter,
			OutputFormatter defaultFormater, EventListener eventListener, Keywords keywords, Map scopeHandlers) {
		Assert.assertNotNull(out);
		Assert.assertNotNull(templateLoader);
		Assert.assertNotNull(contextFactory);
		Assert.assertNotNull(keywords);

		this.out = out;
		this.templateLoader = new TemplateLoaderProxy(templateLoader, this);
		this.contextFactory = contextFactory;
		this.templateNameFilter = templateNameFilter;
		this.defaultFormater = defaultFormater;
		this.eventListener = eventListener;
		this.keywords = keywords;
		this.scopeHandlers = scopeHandlers;

		this.eventPublisher = new EventPublisherImpl(eventListener, this);
		this.localContextStack = new LocalContextStackImpl(out, defaultFormater, eventPublisher, this, keywords, scopeHandlers);
		this.elementStack = new ElementStackImpl(eventPublisher, localContextStack);
		this.templateNameStack = new TemplateStackImpl(eventPublisher, templateNameFilter);
	}

	private final TemplateNameFilter templateNameFilter;

	// 实现 Context -----------

	private final Map scopeHandlers;

	private final Keywords keywords;

	private final EventListener eventListener;

	private final OutputFormatter defaultFormater;

	public Context createContext() {
		Context context = new ContextImpl(out, templateLoader, contextFactory, templateNameFilter, defaultFormater, eventListener, keywords, scopeHandlers);
		context.setLocale(locale);
		context.setTimeZone(timeZone);
		context.setDebug(debug);
		return context;
	}

	private transient final Writer out;

	public Writer getOut() {
		return out;
	}

	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	private TimeZone timeZone;

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		if (timeZone == null)
			timeZone = LocaleUtils.getDefaultTimeZone(locale);
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	private boolean debug;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void clear() {
		super.clear();
		// 清理栈
		clearLocalContexts(); // 此清理将调用各级LocalContext的clear();
		clearTemplates();
		clearEventListeners();
	}

	// 代理 EventPublisher ----------

	private final EventPublisher eventPublisher;

	public void publishEvent(Event event) {
		eventPublisher.publishEvent(event);
	}

	public void addEventListener(EventListener listener) {
		eventPublisher.addEventListener(listener);
	}

	public void clearEventListeners() {
		eventPublisher.clearEventListeners();
	}

	public void removeEventListener(EventListener listener) {
		eventPublisher.removeEventListener(listener);
	}

	// 代理ElementStack ---------------------

	private final ElementStack elementStack;

	public void clearElements() {
		elementStack.clearElements();
	}

	public Element findElement(String name) {
		return elementStack.findElement(name);
	}

	public Element getCurrentElement() {
		return elementStack.getCurrentElement();
	}

	public List getElementStackValues() {
		return elementStack.getElementStackValues();
	}

	public void popElement() {
		elementStack.popElement();
	}

	public void pushElement(Element element) {
		elementStack.pushElement(element);
	}

	// 代理 TemplateStack --------------

	private final TemplateStack templateNameStack;

	public void clearTemplates() {
		templateNameStack.clearTemplates();
	}

	public Template getCurrentTemplate() {
		return templateNameStack.getCurrentTemplate();
	}

	public List getTemplateStackValues() {
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

	public List getLocalContextStackValues() {
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

	// 代理 栈顶的LocalContext --------------

	public LocalContext getParentLocalContext() {
		return getCurrentLocalContext().getParentLocalContext();
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

	public Map getDefinedVariables() {
		return getCurrentLocalContext().getDefinedVariables();
	}

	public boolean isVariableDefined(String name) throws VariableException {
		return getCurrentLocalContext().isVariableDefined(name);
	}

	public boolean isVariableContained(String name) throws VariableException {
		return getCurrentLocalContext().isVariableContained(name);
	}

	public void putNullVariable(String name) throws VariableException {
		getCurrentLocalContext().putNullVariable(name);
	}

	public void putVariable(String name, Object value) throws VariableException {
		getCurrentLocalContext().putVariable(name, value);
	}

	public void putReadonlyVariable(String name, Object value) throws VariableException {
		getCurrentLocalContext().putReadonlyVariable(name, value);
	}

	public void addVariableAlias(String alias, String name) throws VariableException{
		getCurrentLocalContext().addVariableAlias(alias, name);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		getCurrentLocalContext().removeVariableAlias(alias);
	}

	public void setVariable(String name, Object value) throws VariableException {
		getCurrentLocalContext().setVariable(name, value);
	}

	public Object getVariable(String name) throws VariableException {
		return getCurrentLocalContext().getVariable(name);
	}

	public void removeVariable(String name) throws VariableException {
		getCurrentLocalContext().removeVariable(name);
	}

	public void clearVariables() {
		getCurrentLocalContext().clearVariables();
	}

	public Map getVariables() {
		return getCurrentLocalContext().getVariables();
	}

	public void putAllVariables(Map variables) throws VariableException {
		getCurrentLocalContext().putAllVariables(variables);
	}

	public void setVariablesReadonly(boolean readonly) {
		getCurrentLocalContext().setVariablesReadonly(true);
	}

	public boolean isVariablesReadonly() {
		return getCurrentLocalContext().isVariablesReadonly();
	}

	public void clearDefinedVariables() {
		getCurrentLocalContext().clearDefinedVariables();
	}

	public void removeDefinedVariable(String name) throws VariableException {
		getCurrentLocalContext().removeDefinedVariable(name);
	}

	// 代理 StatusStorage ----------

	public boolean getBooleanStatus(String name) {
		return getCurrentLocalContext().getBooleanStatus(name);
	}

	public void setBooleanStatus(String name, boolean value) {
		getCurrentLocalContext().setBooleanStatus(name, value);
	}

	public Object getStatus(String index) {
		return getCurrentLocalContext().getStatus(index);
	}

	public void setStatus(String index, Object value) {
		getCurrentLocalContext().setStatus(index, value);
	}

	public void removeStatus(String index) {
		getCurrentLocalContext().removeStatus(index);
	}

	public Map getStatuses() {
		return getCurrentLocalContext().getStatuses();
	}

	public void clearStatuses() {
		getCurrentLocalContext().clearStatuses();
	}

	// 代理 ObjectStorage -------------

	public void clearProperties() {
		getCurrentLocalContext().clearProperties();
	}

	public Object getProperty(String type, String name) {
		return getCurrentLocalContext().getProperty(type, name);
	}

	public void putProperty(String type, String name, Object value) {
		getCurrentLocalContext().putProperty(type, name, value);
	}

	public void removeProperty(String type, String name) {
		getCurrentLocalContext().removeProperty(type, name);
	}

	public Object getProperty(String name) {
		return getCurrentLocalContext().getProperty(name);
	}

	public void putProperty(String name, Object value) {
		getCurrentLocalContext().putProperty(name, value);
	}

	public void removeProperty(String name) {
		getCurrentLocalContext().removeProperty(name);
	}

	public Map getProperties(String type) {
		return getCurrentLocalContext().getProperties(type);
	}

	public Map getProperties() {
		return getCurrentLocalContext().getProperties();
	}

	// 代理 Factory -----------

	private transient final TemplateLoader templateLoader;

	private transient final ContextFactory contextFactory;

	public Context createContext(Writer out) {
		return contextFactory.createContext(out);
	}

	public GlobalContext getGlobalContext() {
		return contextFactory.getGlobalContext();
	}

	public TemplateLoader getTemplateLoader() {
		return templateLoader;
	}

}
