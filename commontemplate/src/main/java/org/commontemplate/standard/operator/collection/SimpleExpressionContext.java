package org.commontemplate.standard.operator.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.commontemplate.core.DefinedException;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableException;
import org.commontemplate.util.TypeUtils;

/**
 * 简单表达式变量上下文实现
 *
 * @author liangfei0201@163.com
 *
 */
public class SimpleExpressionContext implements VariableResolver {

	public SimpleExpressionContext() {

	}

	// 断言 -----

	protected void assertVariables(Map model) throws VariableException {
		if (model != null && model.size() > 0) {
			for (Iterator iterator = model.keySet().iterator(); iterator.hasNext();) {
				String name = (String)iterator.next();
				assertVariableName(name);
				if (isDefinedVariable(name))
					throw new DefinedException(name + " 已经定义!", name);
			}
		}
	}

	protected void assertVariableName(String var) throws VariableException {
		if (var == null)
			throw new VariableException("变量名不能为空!", null);

		if (! TypeUtils.isNamed(var))
			throw new VariableException(var + " 不符合命名规范!", var);

	}

	private final Map variablesContainer = new HashMap();

	private final Map aliasContainer = new HashMap();

	private final Set readonlyContainer = new HashSet();

	private boolean isLock = false;

	public void lockVariables() {
		this.isLock = true;
	}

	public void unlockVariables() {
		this.isLock = false;
	}

	public boolean isDefinedVariable(String name) throws VariableException {
		return variablesContainer.containsKey(name);
	}

	public void defineVariable(String name, Object value)
			throws DefinedException, VariableException {
		if (isLock)
			throw new VariableException("容器锁定!", name);
		variablesContainer.put(name, value);
	}

	public void defineVariable(String name) throws DefinedException,
			VariableException {
		defineVariable(name, null);
	}

	public void defineReadonlyVariable(String name, Object value)
			throws DefinedException, VariableException {
		defineVariable(name, value);
		readonlyContainer.add(name);
	}

	public void defineAllVariables(Map variables) throws DefinedException,
			VariableException {
		if (isLock)
			throw new VariableException("容器锁定!", variables.keySet().toString());
		this.variablesContainer.putAll(variables);
	}

	public void defineVariableAlias(String alias, String name)
			throws VariableException {
		aliasContainer.put(alias, name);
	}

	public void assignVariable(String name, Object value)
			throws UndefinedException, VariableException {
		if (isLock)
			throw new VariableException("容器锁定!", name);
		variablesContainer.put(name, value);
	}

	public Object lookupVariable(String name) throws VariableException {
		return variablesContainer.get(name);
	}

	public Map getDefinedVariables() {
		return Collections.unmodifiableMap(variablesContainer);
	}

	public void removeVariableAlias(String alias) throws VariableException {
		aliasContainer.remove(alias);
	}

	public void removeVariable(String var) throws UndefinedException,
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

	public void clearVariables() {
		variablesContainer.clear();
		aliasContainer.clear();
		readonlyContainer.clear();
	}

}
