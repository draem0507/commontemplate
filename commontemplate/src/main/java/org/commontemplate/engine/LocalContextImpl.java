package org.commontemplate.engine;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.Context;
import org.commontemplate.core.DefinedException;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.ObjectStorage;
import org.commontemplate.core.OutputController;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.StatusStorage;
import org.commontemplate.core.UndefinedException;
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

	LocalContextImpl(LocalContext superLocalContext,
			String localContextName, Map variablesContainer, Context context, Writer out, Keywords keywords) {
		Assert.assertNotNull(context);
		Assert.assertNotNull(keywords);
		
		this.superLocalContext = superLocalContext;
		this.localContextName = localContextName;
		variableStorage = new LocalVariableStorageImpl(superLocalContext, variablesContainer, context, keywords);
		statusStorage = new LocalStatusStorageImpl(context);
		objectStorage = new LocalObjectStorageImpl(superLocalContext, context);
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

	public void assignVariable(String name, Object value)
			throws UndefinedException, VariableException {
		variableStorage.assignVariable(name, value);
	}

	public void clearVariables() {
		variableStorage.clearVariables();
	}

	public void defineAllVariables(Map variables)
			throws DefinedException, VariableException {
		variableStorage.defineAllVariables(variables);
	}

	public void defineReadonlyVariable(String name, Object value)
			throws DefinedException, VariableException {
		variableStorage.defineReadonlyVariable(name, value);
	}

	public void defineVariable(String name, Object value)
			throws DefinedException, VariableException {
		variableStorage.defineVariable(name, value);
	}

	public void defineVariable(String name) throws DefinedException,
			VariableException {
		variableStorage.defineVariable(name);
	}
	
	public void defineVariableAlias(String alias, String name) throws VariableException{
		variableStorage.defineVariableAlias(alias, name);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		variableStorage.removeVariableAlias(alias);
	}
	
	public Map getDefinedVariables() {
		return variableStorage.getDefinedVariables();
	}

	public boolean isDefinedVariable(String name) throws VariableException {
		return variableStorage.isDefinedVariable(name);
	}

	public void lockVariables() {
		variableStorage.lockVariables();
	}

	public Object lookupVariable(String name) throws VariableException {
		return variableStorage.lookupVariable(name);
	}

	public void removeVariable(String name) throws UndefinedException,
			VariableException {
		variableStorage.removeVariable(name);
	}

	public void unlockVariables() {
		variableStorage.unlockVariables();
	}
	
	// 状态管理 ---------------

	private final StatusStorage statusStorage;

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
	
	private final ObjectStorage objectStorage;

	public void clearObjects() {
		objectStorage.clearObjects();
	}

	public Object lookupObject(String type, String name) {
		return objectStorage.lookupObject(type, name);
	}

	public Object lookupObject(String name) {
		return objectStorage.lookupObject(name);
	}

	public void putObject(String name, Object value) {
		objectStorage.putObject(name, value);
	}

	public void putObject(String type, String name, Object value) {
		objectStorage.putObject(type, name, value);
	}

	public void removeObject(String type, String name) {
		objectStorage.removeObject(type, name);
	}

	public void removeObject(String name) {
		objectStorage.removeObject(name);
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

}
