package org.commontemplate.standard.debug;

import org.commontemplate.config.Rendition;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.Assert;

/**
 * 调试过程封装体实现
 *
 * @author linagfei0201@163.com
 *
 */
final class ExecutionImpl implements Execution, Comparable {

	private final Context context;

	private final Element element;

	private final String threadName;

	public ExecutionImpl(Rendition rendition, String threadName) {
		this.context = rendition.getContext();
		this.element = rendition.getElement();
		this.threadName = threadName;
	}

	public static final int SUSPENDED = 0;

	public static final int STEP_INTO = 1;

	public static final int STEP_OVER = 2;

	public static final int STEP_RETURN = 3;

	public static final int RESUME = 4;

	public static final int RESUME_ALL = 5;

	public static final int TERMINATE = 6;

	private int status = SUSPENDED;

	public int getStatus() {
		return status;
	}

	private void setStatus(int status) {
		Assert.assertTrue(status >= STEP_INTO && status <= TERMINATE, "DebugFrame.invaild.status", new Object[]{new Integer(status)});
		this.status = status;
	}

	/**
	 * 获取被挂起的上下文
	 *
	 * @return 上下文
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * 获取被挂起时正在运行的模板元素
	 *
	 * @return 模板元素
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * 是否为挂起状态, 只有在挂起状态, step, resume, stop等动作才有效
	 *
	 * @return 是否为挂起状态
	 */
	public synchronized boolean isSuspended() {
		return status == SUSPENDED;
	}

	/**
	 * 单步运行
	 */
	public synchronized void stepInto() {
		if (isSuspended()) {
			setStatus(STEP_INTO);
			this.notifyAll();
		}
	}

	/**
	 * 单步块运行
	 */
	public synchronized void stepOver() {
		if (isSuspended()) {
			setStatus(STEP_OVER);
			this.notifyAll();
		}
	}

	/**
	 * 单步跳出上层运行
	 */
	public synchronized void stepReturn() {
		if (isSuspended()) {
			setStatus(STEP_RETURN);
			this.notifyAll();
		}
	}

	/**
	 * 恢复运行直至下一断点
	 */
	public synchronized void resume() {
		if (isSuspended()) {
			setStatus(RESUME);
			this.notifyAll();
		}
	}

	/**
	 * 恢复运行并跳过所有断点
	 */
	public synchronized void resumeAll() {
		if (isSuspended()) {
			setStatus(RESUME_ALL);
			this.notifyAll();
		}
	}

	/**
	 * 停止运行
	 */
	public synchronized void terminate() {
		if (isSuspended()) {
			setStatus(TERMINATE);
			this.notifyAll();
		}
	}

	/**
	 * 获取运行线程名
	 *
	 * @return 运行线程名
	 */
	public String getThreadName() {
		return threadName;
	}

	public int compareTo(Object o) {
		ExecutionImpl e = ((ExecutionImpl)o);
		if (this.threadName == null && e.threadName == null)
			return 0;
		if (this.threadName == null)
			return -1;
		if (e.threadName == null)
			return 1;
		return this.threadName.compareTo(e.threadName);
	}

	public String toString() {
		StringBuffer text = new StringBuffer();
		text.append(getThreadName());
		Context context = getContext();
		if (context != null) {
			Template template = context.getCurrentTemplate();
			if (template != null) {
				text.append(" : ");
				text.append(template.getName());
			}
		}
		return text.toString();
	}

}
