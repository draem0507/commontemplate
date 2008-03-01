package org.commontemplate.engine.expression;

import org.commontemplate.core.Variable;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 变量实现类
 * 
 * @author liangfei0201@163.com
 *
 */
final class VariableImpl extends Variable {

	private static final long serialVersionUID = 1L;

	private final String name;
	
	private final Location location;

	VariableImpl(String name, Location location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getCanonicalForm() {
		return name;
	}

	public Location getLocation() {
		return location;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
