package org.commontemplate.ext.directive.taglib;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * BodyContent适配实现
 * 
 * @author liangfei0201@163.com
 *
 */
class BodyContentImpl extends BodyContent {

	private static final char[] NEWLINE = 
			System.getProperty("line.separator").toCharArray();

	private CharArrayWriter buf;

	BodyContentImpl(JspWriter out, boolean buffer) {
		super(out);
		if (buffer)
			buf = new CharArrayWriter();
	}

	public void flush() throws IOException {
		if (buf == null) {
			getEnclosingWriter().flush();
		}
	}

	public void clear() throws IOException {
		if (buf != null) {
			buf = new CharArrayWriter();
		} else {
			throw new IOException("Can't clear");
		}
	}

	public void clearBuffer() throws IOException {
		if (buf != null) {
			buf = new CharArrayWriter();
		} else {
			throw new IOException("Can't clear");
		}
	}

	public int getRemaining() {
		return Integer.MAX_VALUE;
	}

	public void newLine() throws IOException {
		write(NEWLINE);
	}

	public void close() throws IOException {
		// do nothing
	}

	public void print(boolean value) throws IOException {
		write(value ? Boolean.TRUE.toString() : Boolean.FALSE.toString());
	}

	public void print(char value) throws IOException {
		write(value);
	}

	public void print(char[] value) throws IOException {
		write(value);
	}

	public void print(double value) throws IOException {
		write(Double.toString(value));
	}

	public void print(float value) throws IOException {
		write(Float.toString(value));
	}

	public void print(int value) throws IOException {
		write(Integer.toString(value));
	}

	public void print(long value) throws IOException {
		write(Long.toString(value));
	}

	public void print(Object value) throws IOException {
		write(value == null ? "null" : value.toString());
	}

	public void print(String value) throws IOException {
		write(value);
	}

	public void println() throws IOException {
		newLine();
	}

	public void println(boolean value) throws IOException {
		print(value);
		newLine();
	}

	public void println(char value) throws IOException {
		print(value);
		newLine();
	}

	public void println(char[] value) throws IOException {
		print(value);
		newLine();
	}

	public void println(double value) throws IOException {
		print(value);
		newLine();
	}

	public void println(float value) throws IOException {
		print(value);
		newLine();
	}

	public void println(int value) throws IOException {
		print(value);
		newLine();
	}

	public void println(long value) throws IOException {
		print(value);
		newLine();
	}

	public void println(Object value) throws IOException {
		print(value);
		newLine();
	}

	public void println(String value) throws IOException {
		print(value);
		newLine();
	}

	public void write(int c) throws IOException {
		if (buf != null) {
			buf.write(c);
		} else {
			getEnclosingWriter().write(c);
		}
	}

	public void write(char[] cbuf, int off, int len) throws IOException {
		if (buf != null) {
			buf.write(cbuf, off, len);
		} else {
			getEnclosingWriter().write(cbuf, off, len);
		}
	}

	public String getString() {
		return buf.toString();
	}

	public Reader getReader() {
		return new CharArrayReader(buf.toCharArray());
	}

	public void writeOut(Writer out) throws IOException {
		buf.writeTo(out);
	}

}