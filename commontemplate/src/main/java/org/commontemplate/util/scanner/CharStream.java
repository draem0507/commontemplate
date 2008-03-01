package org.commontemplate.util.scanner;

import java.io.IOException;

/**
 * 字符供应带
 * (迭代子模式)
 * @author liangfei0201@163.com
 *
 */
public interface CharStream {
	
	public static final char END = '\0';

	/**
	 * 取下一字符，已到结尾则返回CharStream.END
	 * @return 下一字符
	 */
	public char nextChar() throws IOException;

}
