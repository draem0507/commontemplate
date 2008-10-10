package org.commontemplate.core;

/**
 * 静态内容元素
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Content extends Element {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取内容
	 *
	 * @return 内容
	 */
	public abstract String getValue();

}
