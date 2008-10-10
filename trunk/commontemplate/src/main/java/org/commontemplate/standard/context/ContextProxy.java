package org.commontemplate.standard.context;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Event;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.core.VariableException;

/**
 * 上下文代理基类
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class ContextProxy extends Context {

	private static final long serialVersionUID = 1L;
	
	protected final Context context;

	public ContextProxy(Context context) {
		super();
		this.context = context;
	}

	public void addEventListener(EventListener listener) {
		context.addEventListener(listener);
	}

	public void addVariableAlias(String alias, String name)
			throws VariableException {
		context.addVariableAlias(alias, name);
	}

	public void clear() {
		context.clear();
	}

	public void clearEventListeners() {
		context.clearEventListeners();
	}

	public void clearDefinedVariables() {
		context.clearDefinedVariables();
	}

	public void clearLocalContexts() {
		context.clearLocalContexts();
	}

	public void clearOutputFormatters() {
		context.clearOutputFormatters();
	}

	public void clearProperties() {
		context.clearProperties();
	}

	public void clearStatuses() {
		context.clearStatuses();
	}

	public void clearTemplates() {
		context.clearTemplates();
	}

	public void clearVariables() {
		context.clearVariables();
	}

	public boolean containsKey(Object key) {
		return context.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return context.containsValue(value);
	}

	public Context createContext() {
		return context.createContext();
	}

	public Context createContext(Writer out) {
		return context.createContext(out);
	}

	public Set entrySet() {
		return context.entrySet();
	}

	public boolean equals(Object obj) {
		return context.equals(obj);
	}

	public LocalContext findLocalContext(String name) {
		return context.findLocalContext(name);
	}

	public Template findTemplate(String name) {
		return context.findTemplate(name);
	}

	public String format(Object content) {
		return context.format(content);
	}

	public Object get(Object key) {
		return context.get(key);
	}

	public boolean getBooleanStatus(String name) {
		return context.getBooleanStatus(name);
	}

	public LocalContext getCurrentLocalContext() {
		return context.getCurrentLocalContext();
	}

	public Template getCurrentTemplate() {
		return context.getCurrentTemplate();
	}

	public Map getDefinedVariables() {
		return context.getDefinedVariables();
	}

	public OutputFormatter getGeneralOutputFormatter() {
		return context.getGeneralOutputFormatter();
	}

	public GlobalContext getGlobalContext() {
		return context.getGlobalContext();
	}

	public String getLocalContextName() {
		return context.getLocalContextName();
	}

	public List getLocalContextStackValues() {
		return context.getLocalContextStackValues();
	}

	public Locale getLocale() {
		return context.getLocale();
	}

	public Writer getOut() {
		return context.getOut();
	}

	public OutputFilter getOutputFilter() {
		return context.getOutputFilter();
	}

	public OutputFormatter getOutputFormatter(Class type) {
		return context.getOutputFormatter(type);
	}

	public LocalContext getParentLocalContext() {
		return context.getParentLocalContext();
	}

	public Map getProperties() {
		return context.getProperties();
	}

	public Map getProperties(String type) {
		return context.getProperties(type);
	}

	public Object getProperty(String type, String name) {
		return context.getProperty(type, name);
	}

	public Object getProperty(String name) {
		return context.getProperty(name);
	}

	public LocalContext getRootLocalContext() {
		return context.getRootLocalContext();
	}

	public Object getStatus(String name) {
		return context.getStatus(name);
	}

	public Map getStatuses() {
		return context.getStatuses();
	}

	public TemplateLoader getTemplateLoader() {
		return context.getTemplateLoader();
	}

	public List getTemplateStackValues() {
		return context.getTemplateStackValues();
	}

	public TimeZone getTimeZone() {
		return context.getTimeZone();
	}

	public Object getVariable(String name) throws VariableException {
		return context.getVariable(name);
	}

	public Map getVariables() {
		return context.getVariables();
	}

	public int hashCode() {
		return context.hashCode();
	}

	public boolean isDebug() {
		return context.isDebug();
	}

	public boolean isEmpty() {
		return context.isEmpty();
	}

	public boolean isVariableContained(String name) throws VariableException {
		return context.isVariableContained(name);
	}

	public boolean isVariableDefined(String name) throws VariableException {
		return context.isVariableDefined(name);
	}

	public boolean isVariablesReadonly() {
		return context.isVariablesReadonly();
	}

	public Set keySet() {
		return context.keySet();
	}

	public void setVariablesReadonly(boolean readonly) {
		context.setVariablesReadonly(true);
	}

	public void output(Object content) throws IOException {
		context.output(content);
	}

	public void popLocalContext() {
		context.popLocalContext();
	}

	public void popTemplate() {
		context.popTemplate();
	}

	public void publishEvent(Event event) {
		context.publishEvent(event);
	}

	public void pushLocalContext() {
		context.pushLocalContext();
	}

	public void pushLocalContext(Map variablesContainer) {
		context.pushLocalContext(variablesContainer);
	}

	public void pushLocalContext(String name, Map variablesContainer) {
		context.pushLocalContext(name, variablesContainer);
	}

	public void pushLocalContext(String name) {
		context.pushLocalContext(name);
	}

	public void pushTemplate(Template template) {
		context.pushTemplate(template);
	}

	public Object put(Object key, Object value) {
		return context.put(key, value);
	}

	public void put(String key, boolean value) {
		context.put(key, value);
	}

	public void put(String key, byte value) {
		context.put(key, value);
	}

	public void put(String key, char value) {
		context.put(key, value);
	}

	public void put(String key, double value) {
		context.put(key, value);
	}

	public void put(String key, float value) {
		context.put(key, value);
	}

	public void put(String key, int value) {
		context.put(key, value);
	}

	public void put(String key, long value) {
		context.put(key, value);
	}

	public void put(String key, Object value) {
		context.put(key, value);
	}

	public void put(String key, short value) {
		context.put(key, value);
	}

	public void putAll(Map map) {
		context.putAll(map);
	}

	public void putAllVariables(Map variables) throws VariableException {
		context.putAllVariables(variables);
	}

	public void putNullVariable(String name) throws VariableException {
		context.putNullVariable(name);
	}

	public void putProperty(String name, Object value) {
		context.putProperty(name, value);
	}

	public void putProperty(String type, String name, Object value) {
		context.putProperty(type, name, value);
	}

	public void putReadonlyVariable(String name, Object value)
			throws VariableException {
		context.putReadonlyVariable(name, value);
	}

	public void putVariable(String name, Object value) throws VariableException {
		context.putVariable(name, value);
	}

	public String relateTemplateName(String name) {
		return context.relateTemplateName(name);
	}

	public Object remove(Object key) {
		return context.remove(key);
	}

	public void removeEventListener(EventListener listener) {
		context.removeEventListener(listener);
	}

	public void removeDefinedVariable(String name) throws VariableException {
		context.removeDefinedVariable(name);
	}

	public void removeGeneralOutputFormatter() {
		context.removeGeneralOutputFormatter();
	}

	public void removeOutputFilter() {
		context.removeOutputFilter();
	}

	public void removeOutputFormatter(Class type) {
		context.removeOutputFormatter(type);
	}

	public void removeProperty(String type, String name) {
		context.removeProperty(type, name);
	}

	public void removeProperty(String name) {
		context.removeProperty(name);
	}

	public void removeStatus(String name) {
		context.removeStatus(name);
	}

	public void removeVariable(String name) throws VariableException {
		context.removeVariable(name);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		context.removeVariableAlias(alias);
	}

	public void setBooleanStatus(String name, boolean value) {
		context.setBooleanStatus(name, value);
	}

	public void setDebug(boolean debug) {
		context.setDebug(debug);
	}

	public void setGeneralOutputFormatter(OutputFormatter outputFormatter) {
		context.setGeneralOutputFormatter(outputFormatter);
	}

	public void setLocale(Locale locale) {
		context.setLocale(locale);
	}

	public void setOutputFilter(OutputFilter outputFilter) {
		context.setOutputFilter(outputFilter);
	}

	public void setOutputFormatter(Class type, OutputFormatter outputFormatter) {
		context.setOutputFormatter(type, outputFormatter);
	}

	public void setStatus(String name, Object value) {
		context.setStatus(name, value);
	}

	public void setTimeZone(TimeZone timeZone) {
		context.setTimeZone(timeZone);
	}

	public void setVariable(String name, Object value) throws VariableException {
		context.setVariable(name, value);
	}

	public int size() {
		return context.size();
	}

	public String toString() {
		return context.toString();
	}

	public Collection values() {
		return context.values();
	}

	public void clearElements() {
		context.clearElements();
	}

	public Element findElement(String name) {
		return context.findElement(name);
	}

	public Element getCurrentElement() {
		return context.getCurrentElement();
	}

	public List getElementStackValues() {
		return context.getElementStackValues();
	}

	public void popElement() {
		context.popElement();
	}

	public void pushElement(Element element) {
		context.pushElement(element);
	}

}
