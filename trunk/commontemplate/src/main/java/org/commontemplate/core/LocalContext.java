package org.commontemplate.core;

/**
 * 局部上下文区域
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class LocalContext extends BaseContext implements OutputController {

	/**
	 * 获取上级变量上下文
	 *
	 * @return 上级变量上下文, 若当前上下文为根上下文则返回null
	 */
	public abstract LocalContext getSuperLocalContext();

	/**
	 * 获取变量上下文名
	 *
	 * @return 上下文名, 匿名上下文区域返回null
	 */
	public abstract String getLocalContextName();

	/**
	 * 获取局部上下文所属块指令, 如果为块指令上下文, 则返回其指令代理, 否则返回空.
	 *
	 * @return 上下文块指令
	 */
	public abstract Block getLocalContextBlock();

}
