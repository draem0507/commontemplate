package org.commontemplate.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.DefinedException;
import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableException;

/**
 * 全局变量存储器实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class GlobalVariableStorageImpl extends VariableStorageSupport {

	GlobalVariableStorageImpl(Keywords keywords) {
		super(keywords);
	}

	private final Map variablesContainer = new HashMap();
	
	private final Map aliasContainer = new HashMap();

	private final Set readonlyContainer = new HashSet();

	private boolean isLock = false;

	public synchronized void lockVariables() {
		this.isLock = true;
	}
	
	public synchronized void unlockVariables() {
		this.isLock = false;
	}

	public synchronized boolean isDefinedVariable(String name) throws VariableException {
		return variablesContainer.containsKey(name);
	}

	public synchronized void defineVariable(String name, Object value)
			throws DefinedException, VariableException {
		if (isLock)
			throw new VariableException("容器锁定!", name);
		variablesContainer.put(name, value);
	}

	public synchronized void defineVariable(String name) throws DefinedException,
			VariableException {
		defineVariable(name, null);
	}

	public synchronized void defineReadonlyVariable(String name, Object value)
			throws DefinedException, VariableException {
		defineVariable(name, value);
		readonlyContainer.add(name);
	}

	public synchronized void defineAllVariables(Map variables) throws DefinedException,
			VariableException {
		if (isLock)
			throw new VariableException("容器锁定!", variables.keySet().toString());
		this.variablesContainer.putAll(variables);
	}

	public void defineVariableAlias(String alias, String name)
			throws VariableException {
		aliasContainer.put(alias, name);
	}

	public synchronized void assignVariable(String name, Object value)
			throws UndefinedException, VariableException {
		if (isLock)
			throw new VariableException("容器锁定!", name);
		variablesContainer.put(name, value);
	}

	public synchronized Object lookupVariable(String name) throws VariableException {
		return variablesContainer.get(name);
	}
	
	public synchronized Map getDefinedVariables() {
		return Collections.unmodifiableMap(variablesContainer);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		aliasContainer.remove(alias);
	}
	
	public synchronized void removeVariable(String var) throws UndefinedException,
			VariableException {
		if (isLock) 
			throw new VariableException("变量容器锁定! 无法移除：" + var, var);
		assertVariableName(var);
		variablesContainer.remove(var);
		readonlyContainer.remove(var);
		Set dels = new HashSet();
		for (Iterator iterator = aliasContainer.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			if (var.equals(entry.getValue())) {
				dels.add(entry.getKey());
			}
		}
		for (Iterator iterator = dels.iterator(); iterator.hasNext();) {
			Object key = iterator.next();
			aliasContainer.remove(key);
		}
		dels.clear();
	}

	public synchronized void clearVariables() {
		variablesContainer.clear();
		aliasContainer.clear();
		readonlyContainer.clear();
	}

}
