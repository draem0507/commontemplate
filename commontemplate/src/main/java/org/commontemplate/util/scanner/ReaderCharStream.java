package org.commontemplate.util.scanner;

import java.io.IOException;
import java.io.Reader;

/**
 * 从读取器中读取char字符流
 *
 * @author liangfei0201@163.com
 *
 */
public class ReaderCharStream implements CharStream {

	private final Reader reader;

	private final char[] buffer = new char[128];

	private boolean end = false;

	private int length = 0;

	private int index = 0;

	/**
	 * 构造字符流
	 *
	 * @param reader 读取器
	 */
	public ReaderCharStream(Reader reader) {
		this.reader = reader;
	}

	public char nextChar() throws IOException {
		if (end)
			return END;

		if (index >= length) {
			length = reader.read(buffer);
			if (length == -1) {
				end = true;
				return END;
			} else {
				index = 0;
			}
		}
		return buffer[index ++];
	}

}
