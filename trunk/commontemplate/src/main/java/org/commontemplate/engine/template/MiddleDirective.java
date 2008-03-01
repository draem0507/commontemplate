package org.commontemplate.engine.template;

import org.commontemplate.config.MiddleBlockDirectiveHandler;
import org.commontemplate.core.Expression;
import org.commontemplate.util.Location;

/**
 * 中间块指令
 * 
 * @author liangfei0201@163.com
 *
 */
final class MiddleDirective extends StartDirective {

	private static final long serialVersionUID = 1L;
	
	private final String prototype;

	MiddleDirective(String name, Location location, Expression expression, MiddleBlockDirectiveHandler middleNestDirectiveHandler, String prototype, String endPrototype) {
		super(name, location, expression, middleNestDirectiveHandler, endPrototype, endPrototype);
		this.prototype = prototype;
	}
	
	public String getCanonicalForm() {
		return prototype + getCanonicalFormAll();
	}

}