package org.commontemplate.core;

import java.util.Iterator;
import java.util.Map;

/**
 * 局部上下文管理器
 *
 * @author liangfei0201@163.com
 *
 */
public interface LocalContextStack {

	/**
	 * 创建局部上下文
	 */
	public void pushLocalContext();

	/**
	 * 创建局部上下文，并命名
	 *
	 * @param name
	 *            上下文索引名称
	 */
	public void pushLocalContext(String name);

	/**
	 * 创建局部模板块上下文
	 *
	 * @param block
	 *            模板块
	 */
	public void pushLocalContext(Block block);

	/**
	 * 创建局部上下文，并指定变量容器
	 *
	 * @param variablesContainer
	 *            变量容器
	 */
	public void pushLocalContext(Map variablesContainer);

	/**
	 * 创建局部上下文，并命名，并指定变量容器
	 *
	 * @param name
	 *            上下文索引名称
	 * @param variablesContainer
	 *            变量容器
	 */
	public void pushLocalContext(String name, Map variablesContainer);

	/**
	 * 移除栈顶局部上下文
	 */
	public void popLocalContext();

	/**
	 * 获取根局部环境区，即最顶部的LocalContext
	 */
	public LocalContext getRootLocalContext();

	/**
	 * 获取当前局部上下文
	 *
	 * @return 当前局部上下文
	 */
	public LocalContext getCurrentLocalContext();

	/**
	 * 查找最近的相应名称的局部上下文
	 *
	 * @param name
	 *            索引名称
	 * @return 相应名称的局部上下文, 未找到时返回null
	 */
	public LocalContext findLocalContext(String name);

	/**
	 * 查看局部上下文栈值
	 *
	 * @return LocalContext栈值, 类型: Iterator&lt;LocalContext&gt;
	 */
	public Iterator getLocalContextStackValues();

	/**
	 * 清空所有局部上下文
	 */
	public void clearLocalContexts();

}
