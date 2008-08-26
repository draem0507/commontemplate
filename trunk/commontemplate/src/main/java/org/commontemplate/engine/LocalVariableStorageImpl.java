package org.commontemplate.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.Context;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.ReadonlyException;
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

	private static final long serialVersionUID = 1L;

	// 上级局部上下文, 当前为根级时为空
	private final LocalContext superLocalContext;

	// 当前上下文, 不为空
	private final Context context;

	// 全局上下文, 不为空
	private final GlobalContext globalContext;

	// 变量容器, 不为空
	private final Map variablesContainer;

	// 变量别名容器, 不为空
	private final Map aliasContainer = new HashMap();

	// 只读变量容器, 不为空
	private final Set readonlyContainer = new HashSet();

	// 变量容器只读锁
	private boolean lock = false;

	LocalVariableStorageImpl(LocalContext superLocalContext, Map variablesContainer, Context context, Keywords keywords) {
		super(keywords);
		this.superLocalContext = superLocalContext;
		this.context = context;
		Assert.assertNotNull(this.context);
		this.globalContext = context.getGlobalContext();
		Assert.assertNotNull(this.globalContext);
		if (variablesContainer != null) {
			this.variablesContainer = variablesContainer;
		} else {
			this.variablesContainer = new HashMap();
		}
	}

	// 变量变化事件
	private void variableChanged(String name, Object oldValue, Object newValue) {
		if (isChanged(oldValue, newValue))
			context.publishEvent(new VariableChangedEvent(this, name, oldValue, newValue));
	}

	// 变量是否变化
	private boolean isChanged(Object oldValue, Object newValue) {
		if (oldValue == null && newValue == null)
			return false;
		if (oldValue == null || newValue == null)
			return true;
		return ! newValue.equals(oldValue);
	}

	public boolean isVariableContained(String name) throws VariableException {
		assertVariableName(name);
		return variablesContainer.containsKey(name);
	}

	public void putVariable(String name, Object value) throws VariableException {
		assertVariableName(name);
		if (lock)
			throw new ReadonlyException(name, "LocalVariableStorageImpl.locked.error");
		if (readonlyContainer.contains(name))
			throw new ReadonlyException(name, "LocalVariableStorageImpl.readonly.error");
		Object old = variablesContainer.get(name);
		variablesContainer.put(name, value);
		variableChanged(name, old, value);
	}

	public void putNullVariable(String name) throws VariableException {
		putVariable(name, null);
	}

	public void putReadonlyVariable(String name, Object value) throws VariableException {
		putVariable(name, value);
		readonlyContainer.add(name); // 注册只读状态
	}

	public void putAllVariables(Map variables) throws VariableException {
		if (variables != null && variables.size() > 0) {
			for (Iterator iterator = variables.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (! (key instanceof String))
					throw new VariableException(String.valueOf(key), "LocalVariableStorageImpl.variable.type.error");
				String name = (String)key;
				putVariable(name, value);
			}
		}
	}

	public void addVariableAlias(String alias, String name)
			throws VariableException {
		assertVariableName(alias);
		assertVariableName(name);
		aliasContainer.put(alias, name); // 注册别名
	}

	public void removeVariableAlias(String alias) throws VariableException {
		assertVariableName(alias);
		aliasContainer.remove(alias);
	}

	public boolean isVariablesLocked() {
		return lock;
	}

	public void lockVariables() {
		this.lock = true;
	}

	public void unlockVariables() {
		lock = false;
	}

	public void setVariable(String name, Object value) throws VariableException {
		if (isVariableContained(name)) {
			putVariable(name, value);
		} else {
			if (superLocalContext != null)
				superLocalContext.setVariable(name, value);
			else
				globalContext.setVariable(name, value);
		}
	}

	public Object getVariable(String name) throws VariableException {
		if (keywords.getParentLocalContextKeyword().equals(name)) {
			if (superLocalContext != null)
				return superLocalContext;
			else
				return globalContext;
		}
		if (keywords.getContextKeyword().equals(name))
			return context;
		assertVariableName(name);
		if (variablesContainer.containsKey(name)) {
			return variablesContainer.get(name);
		} else if (aliasContainer.containsKey(name)) {
			return variablesContainer.get(aliasContainer.get(name));
		} else {
			if (superLocalContext != null)
				return superLocalContext.getVariable(name);
			else
				return globalContext.getVariable(name);
		}
	}

	public void removeVariable(String name) throws VariableException {
		assertVariableName(name);
		if (lock)
			throw new ReadonlyException(name, "LocalVariableStorageImpl.locked.error");
		Object old = variablesContainer.get(name);
		variablesContainer.remove(name); // 移除变量
		readonlyContainer.remove(name); // 移除只读标记
		for (Iterator iterator = aliasContainer.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			if (name.equals(entry.getValue())) {
				iterator.remove(); // 移除别名
			}
		}
		variableChanged(name, old, null);
	}

	public void clearVariables() {
		unlockVariables();
		variablesContainer.clear();
		aliasContainer.clear();
		readonlyContainer.clear();
	}

	public Map getVariables() {
		return Collections.unmodifiableMap(variablesContainer);
	}

	public Map getExistedVariables() {
		Map map = new HashMap();
		if (superLocalContext != null)
			map.putAll(superLocalContext.getExistedVariables());
		else
			map.putAll(globalContext.getExistedVariables());
		map.putAll(variablesContainer);
		return Collections.unmodifiableMap(map);
	}

	public boolean isVariableExisted(String name) throws VariableException {
		if (isVariableContained(name))
			return true;
		if (superLocalContext != null)
			return superLocalContext.isVariableExisted(name);
		return globalContext.isVariableExisted(name);
	}

	public void clearExistedVariables() {
		clearVariables();
		if (superLocalContext != null)
			superLocalContext.clearExistedVariables();
		else
			globalContext.clearExistedVariables();
	}

	public void removeExistedVariable(String name) throws VariableException {
		removeVariable(name);
		if (superLocalContext != null)
			superLocalContext.removeExistedVariable(name);
		else
			globalContext.removeExistedVariable(name);
	}

}
