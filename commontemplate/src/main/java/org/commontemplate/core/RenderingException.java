package org.commontemplate.core;

import java.io.IOException;
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

	private Element element;

	/**
	 * 获取出错的模板元素
	 *
	 * @return 出错的模板元素
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * 获取出错位置
	 *
	 * @return 出错位置
	 */
	public Location getLocation() {
		if (element == null)
			return null;
		return element.getLocation();
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
			s.println("[commontemplate] Error Template Name: " + template.getName()); // TODO 未国际化
		try {
			s.println("[commontemplate] Error Element Source: " + getElement().getSource()); // TODO 未国际化
		} catch (IOException e) {
			// ignore
		}
		s.println("[commontemplate] Error Element Location: " + getLocation()); // TODO 未国际化
		s.println("[commontemplate] Error Message: " + getMessage()); // TODO 未国际化
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		s.println();
		Template template = getTemplate();
		if (template != null)
			s.println("[commontemplate] Error Template Name: " + template.getName()); // TODO 未国际化
		try {
			s.println("[commontemplate] Error Element Source: " + getElement().getSource()); // TODO 未国际化
		} catch (IOException e) {
			// ignore
		}
		s.println("[commontemplate] Error Element Location: " + getLocation()); // TODO 未国际化
		s.println("[commontemplate] Error Message: " + getMessage()); // TODO 未国际化
		super.printStackTrace(s);
	}

}
