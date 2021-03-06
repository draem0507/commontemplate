package org.commontemplate.core;

import java.util.Map;

/**
 * 指令间内部属性存储器.
 * <p/>
 * 与VariableStorage类似，支持逐级查找，
 * 但其不作为变量取值，只是指令间隐含数据的传递。
 *
 * @author liangfei0201@163.com
 *
 */
public interface PropertyStorage {

	// 对象管理 ----

	/**
	 * 层级查找对象的值
	 *
	 * @param name
	 *            对象名称
	 * @return 对象值
	 */
	public Object getProperty(String name);

	/**
	 * 加入对象, 已存在将覆盖
	 *
	 * @param name
	 *            对象名称
	 * @param value
	 *            对象值
	 */
	public void putProperty(String name, Object value);

	/**
	 * 移除对象
	 *
	 * @param name
	 *            对象名称
	 */
	public void removeProperty(String name);

	/**
	 * 获取所有属性值
	 *
	 * @return 所有属性值
	 */
	public Map getProperties();

	// 对象分类管理 ----

	/**
	 * 层级查找分类中对象的值
	 *
	 * @param type
	 *            分类
	 * @param name
	 *            对象名称
	 * @return 对象值
	 */
	public Object getProperty(String type, String name);

	/**
	 * 加入分类对象, 已存在将覆盖
	 *
	 * @param type
	 *            分类
	 * @param name
	 *            对象名称
	 * @param value
	 *            对象值
	 */
	public void putProperty(String type, String name, Object value);

	/**
	 * 移除分类中的对象
	 *
	 * @param type
	 *            分类
	 * @param name
	 *            对象名称
	 */
	public void removeProperty(String type, String name);

	/**
	 * 获取所有属性值
	 *
	 * @param type
	 *            分类
	 * @return 对象值
	 */
	public Map getProperties(String type);

	/**
	 * 清除所有对象及分类对象
	 */
	public void clearProperties();

}
