package org.commontemplate.core;

import java.util.Iterator;
import java.util.Map;

/**
 * 基础上下文，提供仿Map注册接口
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class BaseContext implements Storage {

	/**
	 * 清空上下文, 此动作将清空该上下文持有的所有数据.
	 */
	public void clear() {
		unlockVariables();
		clearVariables();
		clearStatus();
		clearObjects();
	}

	/**
	 * 变量集设值
	 * 
	 * @param map 变量集
	 */
	public void putAll(Map map) {
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			put(String.valueOf(entry.getKey()), entry.getValue());
		}
	}

	/**
	 * 变量设值
	 * 
	 * @param key 变量名
	 * @param value 值
	 */
	public void put(String key, Object value) {
		if (isDefinedVariable(key))
			assignVariable(key, value);
		else 
			defineVariable(key, value);
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
}
