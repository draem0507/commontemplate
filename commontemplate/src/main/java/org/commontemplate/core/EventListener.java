package org.commontemplate.core;

/**
 * 事件监听器
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface EventListener {

	/**
	 * 处理事件
	 * 
	 * @param event
	 *            事件信息
	 */
	public void onEvent(Event event);

}
