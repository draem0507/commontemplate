package org.commontemplate.core;

import java.util.List;

/**
 * 块(拥有子节点的)节点.
 *
 * @author liangfei0201@163.com
 *
 */
public interface Block extends Node {

	/**
	 * 获取模板/块组成元素
	 *
	 * @return 模板/块组成元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

}
