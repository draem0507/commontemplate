package org.commontemplate.core;

import java.util.List;

/**
 * 块指令 <p/> (线程安全)
 * 
 * @author liangfei0201@163.com
 * 
 */
public abstract class BlockDirective extends Directive {

	/**
	 * 获取块指令内部元素
	 * 
	 * @return 内部元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

}
