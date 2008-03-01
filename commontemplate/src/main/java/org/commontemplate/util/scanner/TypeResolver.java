package org.commontemplate.util.scanner;

/**
 * 输入char的类型判定
 * @author liangfei0201@163.com
 *
 */
public interface TypeResolver {
	
	/**
	 * 获取输入字节的类型
	 * @param ch 输入的字节
	 * @return 字节的类型
	 */
	public int getType(char ch);
	
}
