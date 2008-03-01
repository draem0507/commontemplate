package org.commontemplate.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.Context;
import org.commontemplate.core.DefinedException;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableException;
import org.commontemplate.core.event.VariableChangedEvent;
import org.commontemplate.util.Assert;

/**
 * 局部变量存储器实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class LocalVariableStorageImpl extends VariableStorageSupport {

	private final LocalContext superLocalContext;

	private final Context context;
	
	LocalVariableStorageImpl(LocalContext superLocalContext, Map variablesContainer, Context context, Keywords keywords) {
		super(keywords);
		Assert.assertNotNull(context);
		
		this.superLocalContext = superLocalContext;
		this.context = context;
		this.variablesContainer = (variablesContainer == null ? new HashMap() : variablesContainer);
	}

	private final Map variablesContainer;
	
	private final Map aliasContainer = new HashMap();

	private final Set readonlyContainer = new HashSet();
	
	private void variableChanged(String name, Object oldValue, Object newValue) {
		if ((newValue == null && oldValue != null)
				|| (newValue != null && ! newValue.equals(oldValue))) {
			context.publishEvent(new VariableChangedEvent(this, name, oldValue, newValue));
		}
	}

	public boolean isDefinedVariable(String var) throws VariableException {
		assertVariableName(var);
		return variablesContainer.containsKey(var);
	}

	public void defineVariable(String var, Object obj) throws DefinedException, VariableException {
		if (isLock) 
			throw new VariableException("变量容器锁定! 无法定义：" + var, var);
		assertVariableName(var);
		if (isDefinedVariable(var)) 
			throw new DefinedException(var + " 已经定义!", var);
		
		variablesContainer.put(var, obj);
		variableChanged(var, null, obj);
	}

	public void defineVariable(String var) throws DefinedException, VariableException {
		defineVariable(var, null);
	}

	public void defineReadonlyVariable(String name, Object value) throws DefinedException, VariableException {
		defineVariable(name, value);
		readonlyContainer.add(name);
	}

	public void defineVariableAlias(String alias, String name)
			throws VariableException {
		aliasContainer.put(alias, name);
	}

	public void defineAllVariables(Map variables) throws DefinedException, VariableException {
		if (isLock) 
			throw new VariableException("变量容器锁定! 无法定义：" + variables.keySet().toString(), variables.keySet().toString());
		assertVariables(variables);
		variablesContainer.putAll(variables);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		aliasContainer.remove(alias);
	}

	public void assignVariable(String var, Object obj) throws UndefinedException, VariableException {
		if (isLock) 
			throw new VariableException("变量容器锁定! 无法赋值：" + var, var);
		assertVariableName(var);
		if (isDefinedVariable(var)) {
			if (readonlyContainer.contains(var)) 
				throw new VariableException(var + " 为只读变量!", var);
			Object old = variablesContainer.get(var);
			variablesContainer.put(var, obj);
			variableChanged(var, old, obj);
		} else {
			if (superLocalContext == null)
				context.getGlobalContext().assignVariable(var, obj);
			else
				superLocalContext.assignVariable(var, obj);
		}
	}
	
	private boolean isLock = false;

	public void lockVariables() {
		this.isLock = true;
	}
	
	public void unlockVariables() {
		isLock = false;
	}

	public Object lookupVariable(String var) throws UndefinedException, VariableException {
		if (keywords.getSuperLocalContextKeyword().equals(var))
			if (superLocalContext == null)
				return context.getGlobalContext();
			else
				return superLocalContext;
		if (keywords.getContextKeyword().equals(var))
			return context;
		assertVariableName(var);
		if (isDefinedVariable(var)) {
			return variablesContainer.get(var);
		} else if (aliasContainer.containsKey(var)) {
			return variablesContainer.get(aliasContainer.get(var));
		} else {
			if (superLocalContext == null) 
				return context.getGlobalContext().lookupVariable(var);
			else
				return superLocalContext.lookupVariable(var);
		}
	}

	public void removeVariable(String var) throws UndefinedException, VariableException {
		if (isLock) 
			throw new VariableException("变量容器锁定! 无法移除：" + var, var);
		assertVariableName(var);
		Object old = variablesContainer.get(var);
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
		variableChanged(var, old, null);
	}

	public void clearVariables() {
		variablesContainer.clear();
		aliasContainer.clear();
		readonlyContainer.clear();
	}

	public Map getDefinedVariables() {
		return Collections.unmodifiableMap(variablesContainer);
	}

}
