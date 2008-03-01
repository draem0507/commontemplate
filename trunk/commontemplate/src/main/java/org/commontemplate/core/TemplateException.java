package org.commontemplate.core;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.commontemplate.util.Location;

/**
 * 模板异常
 * 
 * @author liangfei@0201@163.com
 * 
 */
public class TemplateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Resource resource;

	private Location location;

	public TemplateException(Resource resource, Location location) {
		super();
		this.resource = resource;
		this.location = location;
	}

	public TemplateException(Resource resource, Location location,
			String message) {
		super(message);
		this.resource = resource;
		this.location = location;
	}

	public TemplateException(Resource resource, Location location,
			Throwable cause) {
		super(cause);
		this.resource = resource;
		this.location = location;
	}

	public TemplateException(Resource resource, Location location,
			String message, Throwable cause) {
		super(message, cause);
		this.resource = resource;
		this.location = location;
	}

	public Resource getResource() {
		return resource;
	}

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
