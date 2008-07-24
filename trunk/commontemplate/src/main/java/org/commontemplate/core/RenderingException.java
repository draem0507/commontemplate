package org.commontemplate.core;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.commontemplate.util.I18nRuntimeException;
import org.commontemplate.util.Location;

/**
 * 模板渲染异常
 *
 * @author liangfei0201@163.com
 *
 */
public class RenderingException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public RenderingException(Element element) {
		super();
		this.element = element;
	}

	public RenderingException(Element element, String message) {
		super(message);
		this.element = element;
	}

	public RenderingException(Element element, String message, Object[] args) {
		super(message, args);
		this.element = element;
	}

	public RenderingException(Element element, Throwable cause) {
		super(cause);
		this.element = element;
	}

	public RenderingException(Element element, String message, Throwable cause) {
		super(message, cause);
		this.element = element;
	}

	public RenderingException(Element element, String message, Object[] args, Throwable cause) {
		super(message, args, cause);
		this.element = element;
	}

	public RenderingException(Element element, Context context,
			Location location, String message, Throwable cause) {
		super(message, cause);
		this.element = element;
		this.context = context;
		this.location = location;
	}

	/**
	 * 获取出错的模板
	 *
	 * @return 出错的模板
	 */
	public Template getTemplate() {
		if (element == null)
			return null;
		return element.getTemplate();
	}

	private Element element;

	/**
	 * 获取出错的模板元素
	 *
	 * @return 出错的模板元素
	 */
	public Element getElement() {
		return element;
	}

	private Location location;

	/**
	 * 获取出错位置
	 *
	 * @return 出错位置
	 */
	public Location getLocation() {
		if (location == null
				&& element != null)
			return element.getLocation();
		return location;
	}

	private Context context;

	/**
	 * 获取出错时的上下文
	 *
	 * @return 出错时的上下文
	 */
	public Context getContext() {
		return context;
	}

	public void printStackTrace(PrintStream s) {
		// printStackTrace(new PrintWriter(new OutputStreamWriter(s)));
		s.println();
		Template template = getTemplate();
		if (template != null)
			s.println("[commontemplate] Error Template Name: " + template.getName());
		s.println("[commontemplate] Error Template Location: " + location);
		s.println("[commontemplate] Error Message: " + getMessage());
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		s.println();
		Template template = getTemplate();
		if (template != null)
			s.println("[commontemplate] Error Template Name: " + template.getName());
		s.println("[commontemplate] Error Template Location: " + location);
		s.println("[commontemplate] Error Message: " + getMessage());
		super.printStackTrace(s);
	}

}
