package org.commontemplate.engine.template;

import org.commontemplate.core.Text;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 文本块/不解释块元素实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class TextImpl extends Text {

	private static final long serialVersionUID = 1L;
	
	private final String name;
	
	private final Location location;
	
	private final String text;
	
	TextImpl(String name, Location location, String text) {
		this.name = name;
		this.location = location;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public String getCanonicalForm() {
		return getValue();
	}

	public Location getLocation() {
		return location;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getValue() {
		return text;
	}

}
