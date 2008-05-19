package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.Block;
import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.OutputController;
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

	private final LocalContext superLocalContext;

	private final String localContextName;

	private final Block block;

	LocalContextImpl(LocalContext superLocalContext,
			String localContextName, Block block, Map variablesContainer, Context context, Writer out, Keywords keywords) {
		Assert.assertNotNull(context);
		Assert.assertNotNull(keywords);

		this.superLocalContext = superLocalContext;
		this.localContextName = localContextName;
		this.block = block;
		variableStorage = new LocalVariableStorageImpl(superLocalContext, variablesContainer, context, keywords);
		statusStorage = new LocalStatusStorageImpl(context);
		objectStorage = new LocalPropertyStorageImpl(superLocalContext, context);
		outputController = new OutputControllerImpl(superLocalContext, context, out);
	}

	public LocalContext getSuperLocalContext() {
		return superLocalContext;
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

	public void clearStatus() {
		statusStorage.clearStatus();
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

	// 对象管理 --------------

	private final PropertyStorage objectStorage;

	public void clearProperties() {
		objectStorage.clearProperties();
	}

	public Object getProperty(String type, String name) {
		return objectStorage.getProperty(type, name);
	}

	public Object getProperty(String name) {
		return objectStorage.getProperty(name);
	}

	public void putProperty(String name, Object value) {
		objectStorage.putProperty(name, value);
	}

	public void putProperty(String type, String name, Object value) {
		objectStorage.putProperty(type, name, value);
	}

	public void removeProperty(String type, String name) {
		objectStorage.removeProperty(type, name);
	}

	public void removeProperty(String name) {
		objectStorage.removeProperty(name);
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

	public void clear() {
		super.clear();
		clearOutputFormatters();
	}

	public Block getLocalContextBlock() {
		return block;
	}

}
