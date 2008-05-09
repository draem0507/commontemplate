package org.commontemplate.core;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.commontemplate.util.Location;

/**
 * 模板解析异常
 * 
 * @author liangfei0201@163.com
 * 
 */
public class ParsingException extends RuntimeException {

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

	public ParsingException(Location location,
			String message, Throwable cause) {
		super(message, cause);
		this.location = location;
	}

	public ParsingException(Resource resource, Location location) {
		super();
		this.resource = resource;
		this.location = location;
	}

	public ParsingException(Resource resource, Location location,
			String message) {
		super(message);
		this.resource = resource;
		this.location = location;
	}

	public ParsingException(Resource resource, Location location,
			Throwable cause) {
		super(cause);
		this.resource = resource;
		this.location = location;
	}

	public ParsingException(Resource resource, Location location,
			String message, Throwable cause) {
		super(message, cause);
		this.resource = resource;
		this.location = location;
	}

	private Resource resource;

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
		s.println("[commontemplate] Error Template Name: " + resource.getName());
		s.println("[commontemplate] Error Template Location: " + location);
		s.println("[commontemplate] Error Message: " + getMessage());
		super.printStackTrace(s);
	}
	
	public void printStackTrace(PrintWriter s) {
		s.println();
		s.println("[commontemplate] Error Template Name: " + resource.getName());
		s.println("[commontemplate] Error Template Location: " + location);
		s.println("[commontemplate] Error Message: " + getMessage());
		super.printStackTrace(s);
	}

}