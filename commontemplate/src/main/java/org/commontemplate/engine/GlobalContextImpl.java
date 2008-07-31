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

	public Map getExistedVariables() {
		return variableStorage.getExistedVariables();
	}

	public boolean isVariableExisted(String name) throws VariableException {
		return variableStorage.isVariableExisted(name);
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

	// 状态管理 ------------

	private final StatusStorage statusStorage = new GlobalStatusStorageImpl();

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

}
