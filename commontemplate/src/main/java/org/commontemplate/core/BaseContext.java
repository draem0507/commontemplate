package org.commontemplate.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 基础上下文，提供仿Map注册接口
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BaseContext implements Map, VariableStorage, StatusStorage, PropertyStorage, Serializable {

	/**
	 * 清空上下文, 此动作将清空该上下文持有的所有数据.
	 */
	public void clear() {
		clearVariables();
		clearStatuses();
		clearProperties();
	}

	protected void finalize() throws Throwable {
		super.finalize();
		clear(); // 保护性释放
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, Object value) {
		putVariable(key, value);
	}

	// 以下为基本类型重载

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, char value) {
		put(key, new Character(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, boolean value) {
		put(key, Boolean.valueOf(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, byte value) {
		put(key, new Byte(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, short value) {
		put(key, new Short(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, int value) {
		put(key, new Integer(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, long value) {
		put(key, new Long(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, float value) {
		put(key, new Float(value));
	}

	/**
	 * 变量设值
	 *
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, double value) {
		put(key, new Double(value));
	}

	// ---- 适配Map接口 ----

	public Object put(Object key, Object value) {
		String name = (key == null ? null : String.valueOf(key));
		Object old = getVariable(name);
		putVariable(name, value);
		return old;
	}

	public void putAll(Map map) {
		putAllVariables(map);
	}

	public Object get(Object key) {
		String name = (key == null ? null : String.valueOf(key));
		return getVariable(name);
	}

	public Object remove(Object key) {
		String name = (key == null ? null : String.valueOf(key));
		Object old = getVariable(name);
		removeVariable(name);
		return old;
	}

	public boolean containsKey(Object key) {
		String name = (key == null ? null : String.valueOf(key));
		return isVariableContained(name);
	}

	public boolean containsValue(Object value) {
		return getVariables().containsValue(value);
	}

	public int size() {
		return getVariables().size();
	}

	public boolean isEmpty() {
		return getVariables().isEmpty();
	}

	public Set entrySet() {
		return getVariables().entrySet();
	}

	public Set keySet() {
		return getVariables().keySet();
	}

	public Collection values() {
		return getVariables().values();
	}

}
