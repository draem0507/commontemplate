package org.commontemplate.core;

/**
 * 模板结构访问者，用于遍历整个模板指令树及表达式树
 * 
 * @author liangfei0201@163.com
 *
 */
public interface Visitor {
	
	/**
	 * 访问到了某个节点
	 * 
	 * @param node 节点
	 * @throws BreakVisitException 当需要停止访问时抛出
	 */
	public void visit(Visitable node) throws BreakVisitException;

}