package org.commontemplate.util.scanner;

import java.io.IOException;
import java.io.Reader;

public class ReaderCharStream implements CharStream {
	
	private final Reader reader;
	
	private final char[] buffer = new char[128];
	
	private boolean end = false;
	
	private int length = 0;
	
	private int index = 0;

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
