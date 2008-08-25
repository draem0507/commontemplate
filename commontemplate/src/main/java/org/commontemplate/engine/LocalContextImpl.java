package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.OutputController;
import org.commontemplate.core.OutputConverter;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.PropertyStorage;
import org.commontemplate.core.StatusStorage;
import org.commontemplate.core.VariableException;
import org.commontemplate.core.VariableStorage;
import org.commontemplate.util.Assert;

/**
 * 局部上下文实现
 *
 * @author liangfei0201@163.com
 *
 */
final class LocalContextImpl extends LocalContext {

	private final LocalContext parentLocalContext;

	private final String localContextName;

	LocalContextImpl(LocalContext parentLocalContext,
			String localContextName, Map variablesContainer, Context context, Writer out, Keywords keywords) {
		Assert.assertNotNull(context);
		Assert.assertNotNull(keywords);

		this.parentLocalContext = parentLocalContext;
		this.localContextName = localContextName;
		variableStorage = new LocalVariableStorageImpl(parentLocalContext, variablesContainer, context, keywords);
		statusStorage = new LocalStatusStorageImpl(context);
		propertyStorage = new LocalPropertyStorageImpl(parentLocalContext, context);
		outputController = new OutputControllerImpl(parentLocalContext, context, out);
	}

	public LocalContext getParentLocalContext() {
		return parentLocalContext;
	}

	public String getLocalContextName() {
		return localContextName;
	}

	// 变量管理 ------

	private final VariableStorage variableStorage;

	public void setVariable(String name, Object value)
			throws VariableException {
		variableStorage.setVariable(name, value);
	}

	public void clearVariables() {
		variableStorage.clearVariables();
	}

	public void putAllVariables(Map variables)
			throws VariableException {
		variableStorage.putAllVariables(variables);
	}

	public void putReadonlyVariable(String name, Object value)
			throws VariableException {
		variableStorage.putReadonlyVariable(name, value);
	}

	public void putVariable(String name, Object value)
			throws VariableException {
		variableStorage.putVariable(name, value);
	}

	public void putNullVariable(String name) throws VariableException {
		variableStorage.putNullVariable(name);
	}

	public void addVariableAlias(String alias, String name) throws VariableException{
		variableStorage.addVariableAlias(alias, name);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		variableStorage.removeVariableAlias(alias);
	}

	public Map getVariables() {
		return variableStorage.getVariables();
	}

	public boolean isVariableContained(String name) throws VariableException {
		return variableStorage.isVariableContained(name);
	}

	public void lockVariables() {
		variableStorage.lockVariables();
	}

	public Object getVariable(String name) throws VariableException {
		return variableStorage.getVariable(name);
	}

	public void removeVariable(String name) throws VariableException {
		variableStorage.removeVariable(name);
	}

	public void unlockVariables() {
		variableStorage.unlockVariables();
	}

	public boolean isVariablesLocked() {
		return variableStorage.isVariablesLocked();
	}

	public void clearExistedVariables() {
		variableStorage.clearExistedVariables();
	}

	public void removeExistedVariable(String name) throws VariableException {
		variableStorage.removeExistedVariable(name);
	}

	// 状态管理 ---------------

	private final StatusStorage statusStorage;

	public boolean getBooleanStatus(String name) {
		return statusStorage.getBooleanStatus(name);
	}

	public void setBooleanStatus(String name, boolean value) {
		statusStorage.setBooleanStatus(name, value);
	}

	public void clearStatuses() {
		statusStorage.clearStatuses();
	}

	public Object getStatus(String name) {
		return statusStorage.getStatus(name);
	}

	public void removeStatus(String name) {
		statusStorage.removeStatus(name);
	}

	public void setStatus(String name, Object value) {
		statusStorage.setStatus(name, value);
	}

	public Map getStatuses() {
		return statusStorage.getStatuses();
	}

	// 对象管理 --------------

	private final PropertyStorage propertyStorage;

	public void clearProperties() {
		propertyStorage.clearProperties();
	}

	public Object getProperty(String type, String name) {
		return propertyStorage.getProperty(type, name);
	}

	public Object getProperty(String name) {
		return propertyStorage.getProperty(name);
	}

	public void putProperty(String name, Object value) {
		propertyStorage.putProperty(name, value);
	}

	public void putProperty(String type, String name, Object value) {
		propertyStorage.putProperty(type, name, value);
	}

	public Map getProperties() {
		return propertyStorage.getProperties();
	}

	public void removeProperty(String type, String name) {
		propertyStorage.removeProperty(type, name);
	}

	public void removeProperty(String name) {
		propertyStorage.removeProperty(name);
	}

	public Map getProperties(String type) {
		return propertyStorage.getProperties(type);
	}

	public Map getExistedVariables() {
		return variableStorage.getExistedVariables();
	}

	public boolean isVariableExisted(String name) throws VariableException {
		return variableStorage.isVariableExisted(name);
	}

	// 输出控制器 -------------

	private final OutputController outputController;

	public void clearOutputFormatters() {
		outputController.clearOutputFormatters();
	}

	public String format(Object content) {
		return outputController.format(content);
	}

	public void output(Object content) throws IOException {
		outputController.output(content);
	}

	public void removeGeneralOutputFormatter() {
		outputController.removeGeneralOutputFormatter();
	}

	public void removeOutputFilter() {
		outputController.removeOutputFilter();
	}

	public void removeOutputFormatter(Class type) {
		outputController.removeOutputFormatter(type);
	}

	public void setGeneralOutputFormatter(OutputFormatter outputFormatter) {
		outputController.setGeneralOutputFormatter(outputFormatter);
	}

	public void setOutputFilter(OutputFilter outputFilter) {
		outputController.setOutputFilter(outputFilter);
	}

	public void setOutputFormatter(Class type,
			OutputFormatter outputFormatter) {
		outputController.setOutputFormatter(type, outputFormatter);
	}

	public OutputFormatter getGeneralOutputFormatter() {
		return outputController.getGeneralOutputFormatter();
	}

	public OutputFilter getOutputFilter() {
		return outputController.getOutputFilter();
	}

	public OutputFormatter getOutputFormatter(Class type) {
		return outputController.getOutputFormatter(type);
	}

	public OutputConverter getOutputConverter() {
		return outputController.getOutputConverter();
	}

	public void removeOutputConverter() {
		outputController.removeOutputConverter();
	}

	public void setOutputConverter(OutputConverter outputConverter) {
		outputController.setOutputConverter(outputConverter);
	}

	public void clear() {
		super.clear();
		clearOutputFormatters();
	}

}
