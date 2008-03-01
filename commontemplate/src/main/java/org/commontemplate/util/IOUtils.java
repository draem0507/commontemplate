package org.commontemplate.util;

import java.io.IOException;
import java.io.Reader;

public class IOUtils {
	
	private IOUtils() {}
	
	public static String readToString(Reader reader) throws IOException {
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[8192];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			buffer.append(buf, 0, len);
		}
		return buffer.toString();
	}
	
	public static char[] readToChars(Reader reader) throws IOException {
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[8192];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			buffer.append(buf, 0, len);
		}
		int length = buffer.length();
		char[] dst = new char[length];
		buffer.getChars(0, length, dst, 0);
		return dst;
	}
	
	public static byte[] readToBytes(Reader reader) throws IOException {
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[8192];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			buffer.append(buf, 0, len);
		}
		return buffer.toString().getBytes();
	}

}
