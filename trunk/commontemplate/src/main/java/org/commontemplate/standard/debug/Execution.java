package org.commontemplate.standard.debug;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;

/**
 * 调试过程封装接口 (实现类保证线程安全)
 *
 * @author linagfei0201@163.com
 *
 */
public interface Execution {

	/**
	 * 获取运行线程名
	 *
	 * @return 运行线程名
	 */
	String getThreadName();

	/**
	 * 获取被挂起的上下文
	 *
	 * @return 上下文
	 */
	public Context getContext();

	/**
	 * 获取被挂起时正在运行的模板元素
	 *
	 * @return 模板元素
	 */
	public Element getElement();

	/**
	 * 是否为挂起状态, 只有在挂起状态, step, resume, stop等动作才有效
	 *
	 * @return 是否为挂起状态
	 */
	boolean isSuspended();

	/**
	 * 单步运行
	 */
	void stepInto();

	/**
	 * 单步块运行
	 */
	void stepOver();

	/**
	 * 单步跳出上层运行
	 */
	void stepReturn();

	/**
	 * 恢复运行直至下一断点
	 */
	void resume();

	/**
	 * 恢复运行并跳过所有断点
	 */
	void resumeAll();

	/**
	 * 终止运行
	 */
	void terminate();

}
