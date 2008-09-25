package org.commontemplate.tools.javaxscript;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.script.Bindings;

import org.commontemplate.core.VariableStorage;

/**
 * 变量容器适配: VariableStorage -&gt; Bindings
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateBindings implements Bindings {

	private final VariableStorage context;

	public CommonTemplateBindings(VariableStorage context) {
		this.context = context;
	}

	public boolean containsKey(Object key) {
		return context.isVariableContained(key == null ? null : key.toString());
	}

	public Object get(Object key) {
		return context.getVariable(key == null ? null : key.toString());
	}

	public Object put(String name, Object value) {
		Object old = context.getVariable(name);
		context.putVariable(name, value);
		return old;
	}

	public void putAll(Map<? extends String, ? extends Object> toMerge) {
		context.putAllVariables(toMerge);
	}

	public Object remove(Object key) {
		Object old = context.getVariable(key == null ? null : key.toString());
		context.removeVariable(key == null ? null : key.toString());
		return old;
	}

	public void clear() {
		context.clearVariables();
	}

	public boolean containsValue(Object value) {
		return context.getVariables().containsValue(value);
	}

	@SuppressWarnings("unchecked")
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return context.getVariables().entrySet();
	}

	public boolean isEmpty() {
		return context.getVariables().isEmpty();
	}

	@SuppressWarnings("unchecked")
	public Set<String> keySet() {
		return context.getVariables().keySet();
	}

	public int size() {
		return context.getVariables().size();
	}

	@SuppressWarnings("unchecked")
	public Collection<Object> values() {
		return context.getVariables().values();
	}

}
