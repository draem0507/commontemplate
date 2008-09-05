package org.commontemplate.engine.template;


import org.commontemplate.core.Comment;
import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.Location;

/**
 * 注释代理
 *
 * @author liangfei0201@163.com
 *
 */
final class CommentProxy extends Comment {

	private static final long serialVersionUID = 1L;

	private final CommentImpl comment;

	public CommentProxy(CommentImpl comment) {
		super();
		this.comment = comment;
	}

	public void accept(TemplateVisitor visitor) {
		comment.accept(visitor);
	}

	public boolean equals(Object obj) {
		return comment.equals(obj);
	}

	public Location getLocation() {
		return comment.getLocation();
	}

	public String getName() {
		return comment.getName();
	}

	public String getSignature() {
		return comment.getSignature();
	}

	public String getSource() {
		return comment.getSource();
	}

	public Template getTemplate() {
		return comment.getTemplate();
	}

	public String getValue() {
		return comment.getValue();
	}

	public int hashCode() {
		return comment.hashCode();
	}

	public void render(Context context) throws RenderingException {
		throw I18nExceptionFactory.createUnsupportedOperationException("CommentProxy.cycle.render", new Object[]{getValue()});
	}

	public String toString() {
		return comment.toString();
	}

	CommentImpl getTarget() {
		return comment;
	}

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		comment.accept(visitor, isEnter);
	}

}
