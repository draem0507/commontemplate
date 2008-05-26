package org.commontemplate.standard.directive.debug;

import org.commontemplate.util.Assert;

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
		Assert.assertTrue(status >= STEP_OVER && status <= TERMINATE, "DebugLock.invaild.status", new Object[]{new Integer(status)});
		this.status = status;
	}

}
