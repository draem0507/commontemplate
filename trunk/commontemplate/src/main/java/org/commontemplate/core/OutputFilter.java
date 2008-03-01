package org.commontemplate.core;

/**
 * 输出过滤器
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface OutputFilter {

	/**
	 * 过滤输出内容
	 * 
	 * @param text
	 *            准备输出的内容
	 * @return 过滤后的输出内容
	 */
	public String filter(String text);

}
