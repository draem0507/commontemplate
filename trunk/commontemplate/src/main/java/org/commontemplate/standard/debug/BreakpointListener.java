package org.commontemplate.standard.debug;

/**
 * 断点变化事件监听器
 *
 * @author liangfei0201@163.com
 *
 */
public interface BreakpointListener {

	/**
	 * 当有新的断点加入时触发
	 *
	 * @param event 断点变化事件信息
	 */
	void onBreakpointAdded(BreakpointEvent event);

	/**
	 * 当有断点被删除时触发
	 *
	 * @param event 断点变化事件信息
	 */
	void onBreakpointRemoved(BreakpointEvent event);

}
