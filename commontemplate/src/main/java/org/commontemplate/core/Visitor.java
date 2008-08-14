package org.commontemplate.core;

/**
 * 模板结构访问者，用于遍历整个模板指令树及表达式树
 *
 * @author liangfei0201@163.com
 *
 */
public interface Visitor {

	/**
	 * 访问下一节点
	 */
	public static final int NEXT = 0;

	/**
	 * 停止访问
	 */
	public static final int STOP = 1;

	/**
	 * 越过子节点
	 */
	public static final int SKIP = 2;

	/**
	 * 访问到了某个节点<br>
	 * 注：此方法不应该被直接调用，而是作为回调接口
	 *
	 * @param node 节点
	 * @return 控制参数
	 */
	public int visit(Node node);

}