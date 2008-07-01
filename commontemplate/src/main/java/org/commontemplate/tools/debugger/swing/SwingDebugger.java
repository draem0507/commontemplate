package org.commontemplate.tools.debugger.swing;

import org.commontemplate.standard.debug.DebugEvent;
import org.commontemplate.standard.debug.DebugListenerAdapter;

/**
 * Swing界面调试器
 *
 * @author liangfei0201@163.com
 *
 */
public class SwingDebugger extends DebugListenerAdapter {

	public void onSuspended(DebugEvent event) {
		DebugFrame.createDebugFrame().addExecution(event.getExecution());
	}

	public void onResumed(DebugEvent event) {
		DebugFrame debugFrame = DebugFrame.getDebugFrame();
		if (debugFrame != null)
			debugFrame.removeExecution(event.getExecution());
	}

}