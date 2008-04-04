package org.commontemplate.engine.template;

import org.commontemplate.core.Comment;
import org.commontemplate.core.Visitor;
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

	CommentImpl(String name, Location location, String comment, String prototype) {
		this.name = name;
		this.location = location;
		this.comment = comment;
		this.prototype = prototype;
	}

	public String getCanonicalForm() {
		return prototype;
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getValue() {
		return comment;
	}

}