package org.commontemplate.core;

/**
 * 事件发布器
 *
 * @author liangfei0201@163.com
 *
 */
public interface EventPublisher {

	/**
	 * 发布事件
	 *
	 * @param event
	 *            事件信息
	 */
	public void publishEvent(Event event);

	/**
	 * 注册事件监听器
	 *
	 * @param listener 事件监听器
	 */
	public void addEventListener(EventListener listener);

	/**
	 * 移除事件监听器
	 *
	 * @param listener 事件监听器
	 */
	public void removeEventListener(EventListener listener);

	/**
	 * 清空事件监听器
	 */
	public void clearEventListeners();

}
