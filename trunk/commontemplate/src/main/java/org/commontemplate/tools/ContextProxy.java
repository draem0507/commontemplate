package org.commontemplate.tools;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Block;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Event;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
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

	public void clearExistedVariables() {
		context.clearExistedVariables();
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

	public boolean getBooleanStatus(String name) {
		return context.getBooleanStatus(name);
	}

	public Block getLocalContextBlock() {
		return context.getLocalContextBlock();
	}

	public LocalContext getCurrentLocalContext() {
		return context.getCurrentLocalContext();
	}

	public Template getCurrentTemplate() {
		return context.getCurrentTemplate();
	}

	public Map getExistedVariables() {
		return context.getExistedVariables();
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

	public Writer getOut() {
		return context.getOut();
	}

	public OutputFilter getOutputFilter() {
		return context.getOutputFilter();
	}

	public OutputFormatter getOutputFormatter(Class type) {
		return context.getOutputFormatter(type);
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

	public boolean isVariableContained(String name) throws VariableException {
		return context.isVariableContained(name);
	}

	public boolean isVariableExisted(String name) throws VariableException {
		return context.isVariableExisted(name);
	}

	public boolean isVariablesLocked() {
		return context.isVariablesLocked();
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

	public Object getProperty(String type, String name) {
		return context.getProperty(type, name);
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

	public void pushLocalContext(Block block) {
		context.pushLocalContext(block);
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

	public void removeEventListener(EventListener listener) {
		context.removeEventListener(listener);
	}

	public void removeExistedVariable(String name) throws VariableException {
		context.removeExistedVariable(name);
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

	public String toString() {
		return context.toString();
	}

	public void unlockVariables() {
		context.unlockVariables();
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOprand, Expression rightOprand) {
		return context.createBinaryOperator(operatorName, leftOprand,
				rightOprand);
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		return context.createBlockDirective(name, expression, elements);
	}

	public Comment createComment(String comment) {
		return context.createComment(comment);
	}

	public Constant createConstant(Object constantValue) {
		return context.createConstant(constantValue);
	}

	public Directive createDirective(String name, Expression expression) {
		return context.createDirective(name, expression);
	}

	public Text createText(String text) {
		return context.createText(text);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression oprand) {
		return context.createUnaryOperator(operatorName, oprand);
	}

	public Variable createVariable(String variableName) {
		return context.createVariable(variableName);
	}

	public ExpressionBuilder getExpressionBuilder() {
		return context.getExpressionBuilder();
	}

	public TemplateBudiler getTemplateBudiler() {
		return context.getTemplateBudiler();
	}

	public Template createTemplate(String name, List elements) {
		return context.createTemplate(name, elements);
	}

}
