package org.commontemplate.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.InvaildVariableNameException;
import org.commontemplate.core.ReadonlyException;
import org.commontemplate.core.UndefinedException;
import org.commontemplate.core.VariableException;

/**
 * 全局变量存储器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class GlobalVariableStorageImpl extends VariableStorageSupport {

	private static final long serialVersionUID = 1L;

	// 变量容器, 不为空
	private final Map variablesContainer = new HashMap();

	// 变量别名容器, 不为空
	private final Map aliasContainer = new HashMap();

	// 只读变量容器, 不为空
	private final Set readonlyContainer = new HashSet();

	// 变量容器只读锁
	private boolean readonly = false;

	GlobalVariableStorageImpl(Keywords keywords) {
		super(keywords);
	}

	public synchronized void setVariablesReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public synchronized boolean isVariablesReadonly() {
		return readonly;
	}

	public synchronized boolean isVariableContained(String name) throws VariableException {
		assertVariableName(name);
		return variablesContainer.containsKey(name);
	}

	public synchronized void putVariable(String name, Object value)
			throws VariableException {
		assertVariableName(name);
		if (readonly)
			throw new ReadonlyException(name, "GlobalVariableStorageImpl.locked.error");
		if (readonlyContainer.contains(name))
			throw new ReadonlyException(name, "GlobalVariableStorageImpl.readonly.error");
		variablesContainer.put(name, value);
	}

	public synchronized void putNullVariable(String name) throws VariableException {
		putVariable(name, null);
	}

	public synchronized void putReadonlyVariable(String name, Object value)
			throws VariableException {
		putVariable(name, value);
		readonlyContainer.add(name);
	}

	public synchronized void putAllVariables(Map variables) throws VariableException {
		if (variables != null && variables.size() > 0) {
			for (Iterator iterator = variables.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (! (key instanceof String))
					throw new InvaildVariableNameException(String.valueOf(key), "GlobalVariableStorageImpl.variable.type.error");
				String name = (String)key;
				putVariable(name, value);
			}
		}
	}

	public synchronized void addVariableAlias(String alias, String name)
			throws VariableException {
		assertVariableName(alias);
		assertVariableName(name);
		aliasContainer.put(alias, name);
	}

	public synchronized void setVariable(String name, Object value)
			throws VariableException {
		if (isVariableContained(name)) {
			if (readonly)
				throw new ReadonlyException(name, "GlobalVariableStorageImpl.locked.error");
			variablesContainer.put(name, value);
		}
	}

	public synchronized Object getVariable(String name) throws VariableException {
		assertVariableName(name);
		if (! variablesContainer.containsKey(name))
			throw new UndefinedException(name);
		return variablesContainer.get(name);
	}

	public synchronized Map getVariables() {
		return Collections.unmodifiableMap(variablesContainer);
	}

	public synchronized void removeVariableAlias(String alias) throws VariableException {
		assertVariableName(alias);
		aliasContainer.remove(alias);
	}

	public synchronized void removeVariable(String name) throws VariableException {
		assertVariableName(name);
		if (readonly)
			throw new ReadonlyException(name, "GlobalVariableStorageImpl.locked.error");
		variablesContainer.remove(name);
		readonlyContainer.remove(name);
		for (Iterator iterator = aliasContainer.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			if (name.equals(entry.getValue())) {
				iterator.remove();
			}
		}
	}

	public synchronized void clearVariables() {
		setVariablesReadonly(false);
		variablesContainer.clear();
		aliasContainer.clear();
		readonlyContainer.clear();
	}

	public synchronized Map getDefinedVariables() {
		return getVariables();
	}

	public synchronized boolean isVariableDefined(String name) throws VariableException {
		return isVariableContained(name);
	}

	public synchronized void clearDefinedVariables() {
		clearVariables();
	}

	public synchronized void removeDefinedVariable(String name) throws VariableException {
		removeVariable(name);
	}

}
