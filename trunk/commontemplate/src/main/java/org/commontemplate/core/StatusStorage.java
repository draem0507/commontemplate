package org.commontemplate.core;

/**
 * 状态管理，只用于同一LocalContext之中状态的设置，其不与上级或下级LocalContext之间交互。
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface StatusStorage {

	/**
	 * 设置状态
	 * 
	 * @param name
	 *            状态索引
	 * @param value
	 *            状态值
	 */
	public void setStatus(String name, Object value);

	/**
	 * 获取状态
	 * 
	 * @param name
	 *            状态索引
	 * @return 状态值, 当状态未设置时返回null.
	 */
	public Object getStatus(String name);

	/**
	 * 移除状态
	 * 
	 * @param name
	 *            状态索引
	 */
	public void removeStatus(String name);

	/**
	 * 清除所有状态
	 */
	public void clearStatus();

}
