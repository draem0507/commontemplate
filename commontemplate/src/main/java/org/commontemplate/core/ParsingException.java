package org.commontemplate.core;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.commontemplate.util.I18nRuntimeException;
import org.commontemplate.util.Location;

/**
 * 模板解析异常
 *
 * @author liangfei0201@163.com
 *
 */
public class ParsingException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public ParsingException(Location location) {
		super();
		this.location = location;
	}

	public ParsingException(Location location,
			String message) {
		super(message);
		this.location = location;
	}

	public ParsingException(Location location,
			Throwable cause) {
		super(cause.getMessage(), cause);
		this.location = location;
	}

	public ParsingException(Location location, String messageKey, Object[] messageArgs) {
		super(messageKey, messageArgs);
		this.location = location;
	}

	public ParsingException(Location location, String messageKey, Object[] messageArgs, Throwable cause) {
		super(messageKey, messageArgs, cause);
		this.location = location;
	}

	public ParsingException(Location location, String message, Throwable cause) {
		super(message, cause);
		this.location = location;
	}

	public ParsingException(Source source, Location location) {
		super();
		this.source = source;
		this.location = location;
	}

	public ParsingException(Source source, Location location,
			String message) {
		super(message);
		this.source = source;
		this.location = location;
	}

	public ParsingException(Source source, Location location,
			Throwable cause) {
		super(cause.getMessage(), cause);
		this.source = source;
		this.location = location;
	}

	public ParsingException(Source source, Location location, String messageKey, Object[] messageArgs) {
		super(messageKey, messageArgs);
		this.source = source;
		this.location = location;
	}

	public ParsingException(Source source, Location location, String messageKey, Object[] messageArgs, Throwable cause) {
		super(messageKey, messageArgs, cause);
		this.source = source;
		this.location = location;
	}

	public ParsingException(Source source, Location location, String message, Throwable cause) {
		super(message, cause);
		this.source = source;
		this.location = location;
	}

	private Source source;

	public void setSource(Source source) {
		this.source = source;
	}

	/**
	 * 获取出错解析源
	 *
	 * @return 出错解析源
	 */
	public Source getSource() {
		return source;
	}

	private Location location;

	/**
	 * 获取出错解析位置
	 *
	 * @return 出错解析位置
	 */
	public Location getLocation() {
		return location;
	}

	public void printStackTrace(PrintStream s) {
		// printStackTrace(new PrintWriter(new OutputStreamWriter(s)));
		s.println();
		if (source != null)
			s.println("[commontemplate] Error occur to Template: " + source.getName());
		if (location != null)
			s.println("[commontemplate] Error occur to Location: " + location);
		if (source != null && location != null) {
			s.println("[commontemplate] Error occur to Block: " + source.getSource(location));
		}
		s.println("[commontemplate] Error Message: " + getMessage());
		s.println("[commontemplate] Error Stack: ");
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		s.println();
		if (source != null)
			s.println("[commontemplate] Error occur to Template: " + source.getName());
		if (location != null)
			s.println("[commontemplate] Error occur to Location: " + location);
		if (source != null && location != null) {
			s.println("[commontemplate] Error occur to Block: " + source.getSource(location));
		}
		s.println("[commontemplate] Error Message: " + getMessage());
		s.println("[commontemplate] Error Stack: ");
		super.printStackTrace(s);
	}

}
