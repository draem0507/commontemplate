package org.commontemplate.core;

import java.io.Serializable;
import java.util.Map;

/**
 * 基础上下文，提供仿Map注册接口
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BaseContext implements VariableStorage, StatusStorage, PropertyStorage, Serializable {

	/**
	 * 清空上下文, 此动作将清空该上下文持有的所有数据.
	 */
	public void clear() {
		clearVariables();
		clearStatuses();
		clearProperties();
	}

	/**
	 * 变量集设值
	 *
	 * @param map 变量集
	 */
	public void putAll(Map map) {
		putAllVariables(map);
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

	protected void finalize() throws Throwable {
		super.finalize();
		clear(); // 保护性释放
	}

}
