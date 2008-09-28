package org.commontemplate.engine.expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.commontemplate.core.VariableException;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Assert;

/**
 * 表达式上下文实现, Map接口适配.
 * (非线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public class ExpressionContext implements Map, VariableResolver {

	private final Map map;

	public ExpressionContext() {
		this.map = new HashMap();
	}

	public ExpressionContext(Map map) {
		Assert.assertNotNull(map);
		this.map = map;
	}

	// ---- 实现VariableResolver ----

	public Object getVariable(String name) throws VariableException {
		return get(name);
	}

	// ---- 代理Map ----

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public Object get(Object key) {
		return map.get(key);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	public void putAll(Map t) {
		map.putAll(t);
	}

	public Object remove(Object key) {
		return map.remove(key);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

	// hashCode() and equals() generate by eclipse

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ExpressionContext other = (ExpressionContext) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}

}
