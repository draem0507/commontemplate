package org.commontemplate.util.scanner;

import java.io.IOException;

/**
 * 状态机扫描字符流 (迭代子模式)
 *
 * @see org.commontemplate.util.scanner.Scanner#scan(CharStream, TokenReceiver)
 * @author liangfei0201@163.com
 *
 */
public interface CharStream {

	/**
	 * 字符流结束符
	 */
	public static final char END = '\0';

	/**
	 * 取下一字符，已到结尾则返回CharStream.END
	 *
	 * @see org.commontemplate.util.scanner.CharStream#END
	 * @return 下一字符
	 */
	public char nextChar() throws IOException;

}
