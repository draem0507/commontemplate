package org.commontemplate.engine.template;

import java.io.IOException;

import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.Location;

/**
 * 行指令代理
 *
 * @author liangfei0201@163.com
 *
 */
class DirectiveProxy extends Directive {

	private static final long serialVersionUID = 2605840464184974573L;

	private final DirectiveImpl directive;

	DirectiveProxy(DirectiveImpl directive) {
		this.directive = directive;
	}

	public void accept(Visitor visitor) {
		directive.accept(visitor);
	}

	public boolean equals(Object obj) {
		return directive.equals(obj);
	}

	public String getSource() throws IOException {
		return directive.getSource();
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

	public void render(Context context) throws RenderingException {
		throw I18nExceptionFactory.createUnsupportedOperationException("DirectiveProxy.cycle.render", new Object[]{getName()});
	}

	public String toString() {
		return directive.toString();
	}

	public Template getTemplate() {
		return directive.getTemplate();
	}

	DirectiveImpl getTarget() {
		return directive;
	}

}
