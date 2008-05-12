package org.commontemplate.engine.template;

import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 行指令代理
 *
 * @author liangfei0201@163.com
 *
 */
class DirectiveProxy extends Directive {

	private static final long serialVersionUID = 2605840464184974573L;

	private final Directive directive;

	DirectiveProxy(Directive directive) {
		this.directive = directive;
	}

	public void accept(Visitor visitor) {
		directive.accept(visitor);
	}

	public boolean equals(Object obj) {
		return directive.equals(obj);
	}

	public String getCanonicalForm() {
		return directive.getCanonicalForm();
	}

	public Expression getExpression() {
		return directive.getExpression();
	}

	public Location getLocation() {
		return directive.getLocation();
	}

	public String getName() {
		return directive.getName();
	}

	public int hashCode() {
		return directive.hashCode();
	}

	public String getSignature() {
		return directive.getSignature();
	}

	public void render(Context context) throws RenderingException {
		throw new java.lang.UnsupportedOperationException("不能在指令处理器内回调render,否则将死循环调用.");
	}

	public String toString() {
		return directive.toString();
	}

}
