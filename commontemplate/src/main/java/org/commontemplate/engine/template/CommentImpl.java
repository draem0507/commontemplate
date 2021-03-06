package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.core.Comment;
import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.StopVisitException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.Location;

/**
 * 注释内容元素实现
 *
 * @author liangfei0201@163.com
 *
 */
final class CommentImpl extends Comment {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Location location;

	private final String comment;

	private final String prototype;

	private final List renderInterceptors;

	private final Comment proxy;

	CommentImpl(String name, Location location, String comment, String prototype, List renderInterceptors) {
		this.name = name;
		this.location = location;
		this.comment = comment;
		this.prototype = prototype;
		this.renderInterceptors = renderInterceptors;
		this.proxy = new CommentProxy(this);
	}

	public void render(Context context) throws RenderingException {
		if (renderInterceptors != null && renderInterceptors.size() > 0)
			new RenditionImpl(proxy, context, renderInterceptors).doRender();
	}

	void doRender(Context context) throws RenderingException {
		// DO NOTHING
	}

	public String getSource() {
		return prototype;
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return comment;
	}

	public String getSignature() {
		return prototype.substring(0,2);
	}

	private Template template;

	public Template getTemplate() {
		return template;
	}

	void setTemplate(Template template) {
		this.template = template;
	}

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		try {
			visitor.visitComment(this);
		} catch (StopVisitException e) {
			if (! isEnter)
				throw e;
		}
	}

}