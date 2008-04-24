package org.commontemplate.standard.directive.debug;

final class DebugLock {

	public static final int STEPPING = 0;

	public static final int STEP_OVER = 1;

	public static final int STEP_INTO = 2;

	public static final int STEP_RETURN = 3;

	public static final int RESUME = 4;

	public static final int TERMINATE = 5;

	private int status = STEPPING;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if (status < STEP_OVER || status > TERMINATE)
			throw new IllegalArgumentException("error status: " + status);
		this.status = status;
	}

}
