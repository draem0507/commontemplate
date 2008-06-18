package org.commontemplate.standard.debug;

import java.util.EventObject;

/**
 * 调试挂起事件事件
 *
 * @author liangfei0201@163.com
 *
 */
public class DebugEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public DebugEvent(Object source, Execution execution) {
		super(source);
		this.execution = execution;
	}

	private final Execution execution;

	public Execution getExecution() {
		return execution;
	}

}
