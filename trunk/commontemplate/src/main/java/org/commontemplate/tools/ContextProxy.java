package org.commontemplate.tools;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.commontemplate.core.Context;
import org.commontemplate.core.DefinedException;
import org.commontemplate.core.Event;
import org.commontemplate.core.Expression;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.NoSuchMessageException;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableException;

/**
 * 上下文代理基类
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class ContextProxy extends Context {

	protected final Context context;

	public ContextProxy(Context context) {
		this.context = context;
	}

	public void assignVariable(String name, Object value)
			throws UndefinedException, VariableException {
		context.assignVariable(name, value);
	}

	public void clear() {
		context.clear();
	}

	public void clearLocalContexts() {
		context.clearLocalContexts();
	}

	public void clearObjects() {
		context.clearObjects();
	}

	public void clearOutputFormatters() {
		context.clearOutputFormatters();
	}

	public void clearStatus() {
		context.clearStatus();
	}

	public void clearTemplates() {
		context.clearTemplates();
	}

	public void clearVariables() {
		context.clearVariables();
	}

	public boolean containsTemplate(String name) {
		return context.containsTemplate(name);
	}

	public Context createContext() {
		return context.createContext();
	}

	public void debug(String msg, Throwable e) {
		context.debug(msg, e);
	}

	public void debug(String msg) {
		context.debug(msg);
	}

	public void defineAllVariables(Map variables) throws DefinedException,
			VariableException {
		context.defineAllVariables(variables);
	}

	public void defineReadonlyVariable(String name, Object value)
			throws DefinedException, VariableException {
		context.defineReadonlyVariable(name, value);
	}

	public void defineVariable(String name, Object value)
			throws DefinedException, VariableException {
		context.defineVariable(name, value);
	}

	public void defineVariable(String name) throws DefinedException,
			VariableException {
		context.defineVariable(name);
	}

	public void defineVariableAlias(String alias, String name)
			throws VariableException {
		context.defineVariableAlias(alias, name);
	}

	public boolean equals(Object obj) {
		return context.equals(obj);
	}

	public void error(String msg, Throwable e) {
		context.error(msg, e);
	}

	public void error(String msg) {
		context.error(msg);
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

	public Iterator getLocalContextStackValues() {
		return context.getLocalContextStackValues();
	}

	public Locale getLocale() {
		return context.getLocale();
	}

	public String getMessage(String key, Object[] args, String defaultValue) {
		return context.getMessage(key, args, defaultValue);
	}

	public String getMessage(String key, Object[] args)
			throws NoSuchMessageException {
		return context.getMessage(key, args);
	}

	public String getMessage(String key, String defaultValue) {
		return context.getMessage(key, defaultValue);
	}

	public String getMessage(String key) throws NoSuchMessageException {
		return context.getMessage(key);
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

	public LocalContext getRootLocalContext() {
		return context.getRootLocalContext();
	}

	public Object getStatus(String name) {
		return context.getStatus(name);
	}

	public LocalContext getSuperLocalContext() {
		return context.getSuperLocalContext();
	}

	public Template getTemplate(String name, String encoding)
			throws IOException, ParsingException {
		return context.getTemplate(name, encoding);
	}

	public Template getTemplate(String name) throws IOException,
			ParsingException {
		return context.getTemplate(name);
	}

	public Iterator getTemplateStackValues() {
		return context.getTemplateStackValues();
	}

	public TimeZone getTimeZone() {
		return context.getTimeZone();
	}

	public int hashCode() {
		return context.hashCode();
	}

	public void info(String msg) {
		context.info(msg);
	}

	public boolean isDebugEnabled() {
		return context.isDebugEnabled();
	}

	public boolean isDebugMode() {
		return context.isDebugMode();
	}

	public boolean isDefinedVariable(String name) throws VariableException {
		return context.isDefinedVariable(name);
	}

	public boolean isErrorEnabled() {
		return context.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return context.isFatalEnabled();
	}

	public boolean isInfoEnabled() {
		return context.isInfoEnabled();
	}

	public boolean isOver() {
		return context.isOver();
	}

	public boolean isStep() {
		return context.isStep();
	}

	public boolean isWarnEnabled() {
		return context.isWarnEnabled();
	}

	public Resource loadResource(String name, String encoding)
			throws IOException {
		return context.loadResource(name, encoding);
	}

	public Resource loadResource(String name) throws IOException {
		return context.loadResource(name);
	}

	public void lockVariables() {
		context.lockVariables();
	}

	public Object lookupObject(String type, String name) {
		return context.lookupObject(type, name);
	}

	public Object lookupObject(String name) {
		return context.lookupObject(name);
	}

	public Object lookupVariable(String name) throws VariableException {
		return context.lookupVariable(name);
	}

	public void output(Object content) throws IOException {
		context.output(content);
	}

	public Expression parseExpression(String expression)
			throws ParsingException {
		return context.parseExpression(expression);
	}

	public Template parseTemplate(Resource resource) throws ParsingException,
			IOException {
		return context.parseTemplate(resource);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return context.parseTemplate(template);
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

	public void putObject(String name, Object value) {
		context.putObject(name, value);
	}

	public void putObject(String type, String name, Object value) {
		context.putObject(type, name, value);
	}

	public String relateTemplateName(String name) {
		return context.relateTemplateName(name);
	}

	public void removeGeneralOutputFormatter() {
		context.removeGeneralOutputFormatter();
	}

	public void removeObject(String type, String name) {
		context.removeObject(type, name);
	}

	public void removeObject(String name) {
		context.removeObject(name);
	}

	public void removeOutputFilter() {
		context.removeOutputFilter();
	}

	public void removeOutputFormatter(Class type) {
		context.removeOutputFormatter(type);
	}

	public void removeStatus(String name) {
		context.removeStatus(name);
	}

	public void removeVariable(String name) throws UndefinedException,
			VariableException {
		context.removeVariable(name);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		context.removeVariableAlias(alias);
	}

	public void setGeneralOutputFormatter(OutputFormatter outputFormatter) {
		context.setGeneralOutputFormatter(outputFormatter);
	}

	public void setOutputFilter(OutputFilter outputFilter) {
		context.setOutputFilter(outputFilter);
	}

	public void setOutputFormatter(Class type, OutputFormatter outputFormatter) {
		context.setOutputFormatter(type, outputFormatter);
	}

	public void setOver(boolean over) {
		context.setOver(over);
	}

	public void setStatus(String name, Object value) {
		context.setStatus(name, value);
	}

	public void setStep(boolean step) {
		context.setStep(step);
	}

	public String toString() {
		return context.toString();
	}

	public void unlockVariables() {
		context.unlockVariables();
	}

	public void warn(String msg, Throwable e) {
		context.warn(msg, e);
	}

	public void warn(String msg) {
		context.warn(msg);
	}

}
