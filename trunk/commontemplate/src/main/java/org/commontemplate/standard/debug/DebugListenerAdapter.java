package org.commontemplate.standard.debug;

public abstract class DebugListenerAdapter implements DebugListener {

	public void onResumeAlled(DebugEvent event) {
		onResumed(event);
	}

	public void onStepIntoed(DebugEvent event) {
		onResumed(event);
	}

	public void onStepOvered(DebugEvent event) {
		onResumed(event);
	}

	public void onStepReturned(DebugEvent event) {
		onResumed(event);
	}

	public void onTerminated(DebugEvent event) {
		onResumed(event);
	}

}
