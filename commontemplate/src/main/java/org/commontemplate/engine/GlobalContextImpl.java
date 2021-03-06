package org.commontemplate.engine;

import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.PropertyStorage;
import org.commontemplate.core.StatusStorage;
import org.commontemplate.core.VariableException;
import org.commontemplate.core.VariableStorage;

/**
 * 全局上下文实现
 *
 * @author liangfei0201@163.com
 *
 */
final class GlobalContextImpl extends GlobalContext {

	private static final long serialVersionUID = 1L;

	GlobalContextImpl(Keywords keywords) {
		variableStorage = new GlobalVariableStorageImpl(keywords);
	}

	// 变量管理 ---------

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

	public void setVariablesReadonly(boolean readonly) {
		variableStorage.setVariablesReadonly(true);
	}

	public Object getVariable(String name) throws VariableException {
		return variableStorage.getVariable(name);
	}

	public void removeVariable(String name) throws VariableException {
		variableStorage.removeVariable(name);
	}

	public Map getDefinedVariables() {
		return variableStorage.getDefinedVariables();
	}

	public boolean isVariableDefined(String name) throws VariableException {
		return variableStorage.isVariableDefined(name);
	}

	public boolean isVariablesReadonly() {
		return variableStorage.isVariablesReadonly();
	}

	public void clearDefinedVariables() {
		variableStorage.clearDefinedVariables();
	}

	public void removeDefinedVariable(String name) throws VariableException {
		variableStorage.removeDefinedVariable(name);
	}

	// 状态管理 ------------

	private final StatusStorage statusStorage = new GlobalStatusStorageImpl();

	public boolean getBooleanStatus(String name) {
		return statusStorage.getBooleanStatus(name);
	}

	public void setBooleanStatus(String name, boolean value) {
		statusStorage.setBooleanStatus(name, value);
	}

	public void clearStatuses() {
		statusStorage.clearStatuses();
	}

	public Map getStatuses() {
		return statusStorage.getStatuses();
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

	// 对象管理 ------------

	private final PropertyStorage propertyStorage = new GlobalPropertyStorageImpl();

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

	public void removeProperty(String type, String name) {
		propertyStorage.removeProperty(type, name);
	}

	public void removeProperty(String name) {
		propertyStorage.removeProperty(name);
	}

	public Map getProperties(String type) {
		return propertyStorage.getProperties(type);
	}

	public Map getProperties() {
		return propertyStorage.getProperties();
	}

}
