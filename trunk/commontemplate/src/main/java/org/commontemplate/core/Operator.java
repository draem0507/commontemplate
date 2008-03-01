package org.commontemplate.core;

import java.util.List;

/**
 * 操作符 <p/> (线程安全)
 * 
 * @author liangfei0201@163.com
 * 
 */
public abstract class Operator extends Expression {

	/**
	 * 获取操作数列表
	 * 
	 * @return 操作数列表, 类型: List&lt;Expression&gt;
	 */
	public abstract List getOperands();

	/**
	 * 获取操作符优先级
	 * 
	 * @return 优先级
	 */
	public abstract int getPriority();
	
}
