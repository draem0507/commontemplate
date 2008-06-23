package org.commontemplate.core;

/**
 * 过滤性模板元素的访问者
 *
 * @author liangfei0201@163.com
 *
 */
public interface FilteredVisitor extends Visitor {

	/**
	 * 判断是否需要访问
	 *
	 * @param node 待访问的节点
	 * @return 是否访问此节点及其子节点
	 */
	public boolean isVisit(Visitable node);

}
