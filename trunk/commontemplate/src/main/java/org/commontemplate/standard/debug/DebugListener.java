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

	void onStepIntoed(DebugEvent event);

	void onStepOvered(DebugEvent event);

	void onStepReturned(DebugEvent event);

	/**
	 * 当挂起的过程恢复运行时触发
	 *
	 * @param event 调试事件信息
	 */
	void onResumed(DebugEvent event);

	void onResumeAlled(DebugEvent event);

	void onTerminated(DebugEvent event);

}
