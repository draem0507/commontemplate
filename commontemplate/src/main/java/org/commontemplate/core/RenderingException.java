package org.commontemplate.core;

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

	public RenderingException(Template template, Context context,
			Location location, String message, Throwable cause) {
		super(message, cause);
		this.template = template;
		this.context = context;
		this.location = location;
	}

	public RenderingException(Template template, Context context,
			Location location, String message) {
		super(message);
		this.template = template;
		this.context = context;
		this.location = location;
	}

	public RenderingException(Template template, Context context,
			Location location, Throwable cause) {
		super(cause);
		this.template = template;
		this.context = context;
		this.location = location;
	}

	public RenderingException(Template template, Context context,
			Location location) {
		super();
		this.template = template;
		this.context = context;
		this.location = location;
	}

	private Template template;

	/**
	 * 获取出错的模板
	 *
	 * @return 出错的模板
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * 设置出错的模板
	 *
	 * @param template
	 *            出错的模板
	 */
	public void setTemplate(Template template) {
		this.template = template;
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

}
