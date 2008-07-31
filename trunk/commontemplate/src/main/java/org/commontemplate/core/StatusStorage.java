package org.commontemplate.core;

import java.util.Map;

/**
 * 状态管理器.
 * <p/>
 * 只用于同一LocalContext之中状态的设置，
 * 其不与上级或下级LocalContext之间交互。
 *
 * @author liangfei0201@163.com
 *
 */
public interface StatusStorage {

	/**
	 * 设置布尔型状态
	 *
	 * @param name
	 *            状态索引
	 * @param value
	 *            状态值
	 */
	public void setBooleanStatus(String name, boolean value);

	/**
	 * 获取布尔型状态
	 *
	 * @param name
	 *            状态索引
	 * @return 状态值, 当状态未设置时返回null.
	 */
	public boolean getBooleanStatus(String name);

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
	 * 获取所有状态
	 *
	 * @return 所有状态
	 */
	public Map getStatuses();

	/**
	 * 清除所有状态
	 */
	public void clearStatuses();

}
