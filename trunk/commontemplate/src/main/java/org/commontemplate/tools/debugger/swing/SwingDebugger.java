package org.commontemplate.tools.debugger.swing;

import org.commontemplate.standard.debug.DebugEvent;
import org.commontemplate.standard.debug.DebugListener;

/**
 * Swing界面调试器
 *
 * @author liangfei0201@163.com
 *
 */
public class SwingDebugger implements DebugListener {

	public void onSuspended(DebugEvent event) {
		DebugFrame.createDebugFrame().addExecution(event.getExecution());
	}

	public void onExecuted(DebugEvent event) {
		DebugFrame debugFrame = DebugFrame.getDebugFrame();
		if (debugFrame != null)
			debugFrame.removeExecution(event.getExecution());
	}

}