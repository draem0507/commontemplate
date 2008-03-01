package org.commontemplate.util.scanner;

import java.io.IOException;

/**
 * 使用字符串作为输入子带
 * @author liangfei0201@163.com
 *
 */
public class StringCharStream implements CharStream {
	
	private final String source;
	
	private int index = 0;

	public StringCharStream(String source) {
		this.source = source;
	}

	public char nextChar() throws IOException {
		if (index >= source.length())
			return END;
		return source.charAt(index ++);
	}

}
