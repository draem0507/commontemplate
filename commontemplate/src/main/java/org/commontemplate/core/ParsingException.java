package org.commontemplate.core;

import java.io.IOException;
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
		super(cause);
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

	private Resource resource;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * 获取出错解析源
	 *
	 * @return 出错解析源
	 */
	public Resource getResource() {
		return resource;
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
		if (resource != null)
			s.println("[commontemplate] Error Template Name: " + resource.getName());
		if (location != null)
			s.println("[commontemplate] Error Template Location: " + location);
		if (resource != null && location != null) {
			try {
				s.println("[commontemplate] Error Template Block: " + resource.getSource(location));
			} catch (IOException e) {
				// ignore
			}
		}
		s.println("[commontemplate] Error Message: " + getMessage());
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		s.println();
		if (resource != null)
			s.println("[commontemplate] Error Template Name: " + resource.getName());
		if (location != null)
			s.println("[commontemplate] Error Template Location: " + location);
		if (resource != null && location != null) {
			try {
				s.println("[commontemplate] Error Template Block: " + resource.getSource(location));
			} catch (IOException e) {
				// ignore
			}
		}
		s.println("[commontemplate] Error Message: " + getMessage());
		super.printStackTrace(s);
	}

}
