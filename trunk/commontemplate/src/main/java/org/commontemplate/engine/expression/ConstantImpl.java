package org.commontemplate.engine.expression;

import org.commontemplate.core.Constant;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 常量实现类
 * 
 * @author liangfei0201@163.com
 *
 */
final class ConstantImpl extends Constant {

	private static final long serialVersionUID = 1L;

	private final Object value;
	
	private final Location location;

	ConstantImpl(Object value, Location location) {
		this.value = value;
		this.location = location;
	}

	public String getName() {
		return String.valueOf(value);
	}
	
	public String getCanonicalForm() {
		if (value instanceof String)
			return "\"" + value + "\"";
		return String.valueOf(value);
	}

	public Location getLocation() {
		return location;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Object getValue() {
		return value;
	}
	
}
