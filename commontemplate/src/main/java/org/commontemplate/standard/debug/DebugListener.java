package org.commontemplate.standard.debug;

/**
 * 调试挂起事件监听器
 *
 * @author liangfei0201@163.com
 *
 */
public interface DebugListener {

	/**
	 * 当模板运行过程被挂起时触发
	 *
	 * @param event 调试事件信息
	 */
	void onSuspended(DebugEvent event);

	/**
	 * 当挂起的过程单步运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onStepIntoed(DebugEvent event);

	/**
	 * 当挂起的过程单步(跳过块)运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onStepOvered(DebugEvent event);

	/**
	 * 当挂起的过程单步(返回上级)运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onStepReturned(DebugEvent event);

	/**
	 * 当挂起的过程恢复运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onResumed(DebugEvent event);

	/**
	 * 当挂起的过程全部恢复运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onResumeAlled(DebugEvent event);

	/**
	 * 当挂起的过程终止运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onTerminated(DebugEvent event);

}
