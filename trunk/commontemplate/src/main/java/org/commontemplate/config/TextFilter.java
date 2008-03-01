package org.commontemplate.config;

/**
 * 非指令文本块过滤器. <p/> 当引擎遇到非指令文本块时，将调用该过滤器并将结果作为固定输出。 注：在编译期执行，而非运行期执行。
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface TextFilter {

	/**
	 * 过滤文本块
	 * 
	 * @param text
	 *            原始文本块
	 * @return 过滤后的文本块, 将以其作为固定输出.
	 */
	public String filter(String text);

}
