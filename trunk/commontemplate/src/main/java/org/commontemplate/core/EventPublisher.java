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

}
