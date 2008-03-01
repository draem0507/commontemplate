package org.commontemplate.engine;

import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.DefinedException;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.ObjectStorage;
import org.commontemplate.core.StatusStorage;
import org.commontemplate.core.UndefinedException;
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
	
	// 状态管理 ------------
	
	private final StatusStorage statusStorage = new GlobalStatusStorageImpl();

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
	
	private final ObjectStorage objectStorage = new GlobalObjectStorageImpl();

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

}
