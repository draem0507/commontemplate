package org.commontemplate.engine.template;

import org.commontemplate.core.Comment;
import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

public class CommentInterceptProxy extends Comment {

	private static final long serialVersionUID = 1L;

	private final CommentImpl comment;

	public CommentInterceptProxy(CommentImpl comment) {
		super();
		this.comment = comment;
	}

	public void accept(Visitor visitor) {
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

	public String getType() {
		return comment.getType();
	}

	public String getValue() {
		return comment.getValue();
	}

	public int hashCode() {
		return comment.hashCode();
	}

	public void render(Context context) throws RenderingException {
		// 绕过拦截器
	}

	public String toString() {
		return comment.toString();
	}

}
