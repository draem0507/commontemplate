package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.MiddleBlockDirectiveHandler;
import org.commontemplate.core.Expression;
import org.commontemplate.util.Location;

/**
 * 中间块指令
 *
 * @author liangfei0201@163.com
 *
 */
final class MiddleBlockDirectiveImpl extends BlockDirectiveImpl {

	private static final long serialVersionUID = 1L;

	private final String prototype;

	MiddleBlockDirectiveImpl(String name, Location location, Expression expression, MiddleBlockDirectiveHandler middleNestDirectiveHandler, String prototype, String endPrototype, List elementInterceptors) {
		super(name, location, expression, middleNestDirectiveHandler, endPrototype, endPrototype, elementInterceptors);
		this.prototype = prototype;
	}

	public String getCanonicalForm() {
		return prototype + getCanonicalFormAll();
	}

}