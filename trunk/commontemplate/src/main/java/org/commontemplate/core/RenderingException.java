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

	public RenderingException(Element element, Context context) {
		super();
		this.element = element;
		this.context = context;
	}

	public RenderingException(Element element, Context context, String message) {
		super(message);
		this.element = element;
		this.context = context;
	}

	public RenderingException(Element element, Context context, String message, Object[] args) {
		super(message, args);
		this.element = element;
		this.context = context;
	}

	public RenderingException(Element element, Context context, Throwable cause) {
		super(cause.getMessage(), cause);
		this.element = element;
		this.context = context;
	}

	public RenderingException(Element element, Context context, String message, Throwable cause) {
		super(message, cause);
		this.element = element;
		this.context = context;
	}

	public RenderingException(Element element, Context context, String message, Object[] args, Throwable cause) {
		super(message, args, cause);
		this.element = element;
		this.context = context;
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
		s.println("[commontemplate] Error occur to Template: " + (template == null ? null : template.getName())); // TODO 未国际化
		s.println("[commontemplate] Error occur to Location: " + getLocation()); // TODO 未国际化
		s.println("[commontemplate] Error occur to Element: " + (element == null ? null : element.getSource())); // TODO 未国际化
		s.println("[commontemplate] Error Message: " + getMessage()); // TODO 未国际化
		s.println("[commontemplate] Error Stack: ");
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		s.println();
		Template template = getTemplate();
		s.println("[commontemplate] Error occur to Template: " + (template == null ? null : template.getName())); // TODO 未国际化
		s.println("[commontemplate] Error occur to Location: " + getLocation()); // TODO 未国际化
		s.println("[commontemplate] Error occur to Element: " + (element == null ? null : element.getSource())); // TODO 未国际化
		s.println("[commontemplate] Error Message: " + getMessage()); // TODO 未国际化
		s.println("[commontemplate] Error Stack: ");
		super.printStackTrace(s);
	}

}
