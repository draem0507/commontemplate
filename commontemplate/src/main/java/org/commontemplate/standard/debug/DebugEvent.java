package org.commontemplate.standard.debug;

import java.util.EventObject;

/**
 * 调试挂起事件信息
 *
 * @author liangfei0201@163.com
 *
 */
public class DebugEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造挂起事件信息
	 *
	 * @param source 事件发布者
	 * @param execution 被挂起的执行过程
	 */
	public DebugEvent(Object source, Execution execution) {
		super(source);
		this.execution = execution;
	}

	private final Execution execution;

	/**
	 * 获取被挂起的执行过程
	 *
	 * @return 被挂起的执行过程
	 */
	public Execution getExecution() {
		return execution;
	}

}
