package org.commontemplate.core;

/**
 * 转出转换器
 *
 * @author liangfei0201@163.com
 *
 */
public interface OutputConverter {

	/**
	 * 转换输出内容.
	 * 注：在格式化之前转换.
	 *
	 * @param msg
	 *            准备输出的内容
	 * @return 转换后的输出内容
	 */
	public Object convert(Object msg);

}
