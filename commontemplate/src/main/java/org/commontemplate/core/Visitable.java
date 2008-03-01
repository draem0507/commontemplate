package org.commontemplate.core;

/**
 * 可被访问的结点
 * 
 * @author liangfei0201@163.com
 *
 */
public interface Visitable {
	
	/**
	 * 接收访问者, 并带领访问者遍历整个树 (中序遍历)
	 * 
	 * @param visitor 访问者
	 */
	public void accept(Visitor visitor) throws BreakVisitException;

}
