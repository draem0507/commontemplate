package org.commontemplate.standard.debug;

import java.util.EventObject;

/**
 * 断点变化事件
 *
 * @author liangfei0201@163.com
 *
 */
public class BreakpointEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public BreakpointEvent(Object source, Breakpoint breakpoint) {
		super(source);
		this.breakpoint = breakpoint;
	}

	private final Breakpoint breakpoint;

	public Breakpoint getBreakpoint() {
		return breakpoint;
	}

}
