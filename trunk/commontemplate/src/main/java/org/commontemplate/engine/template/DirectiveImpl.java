package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 行指令实现
 *
 * @author liangfei0201@163.com
 *
 */
final class DirectiveImpl extends Directive {

	private static final long serialVersionUID = 1L;

	private final Expression expression;

	private final String name;

	private final Location location;

	private final String prototype;

	private final DirectiveHandler directiveHandler;

	private final List elementInterceptors;

	private final Directive handleProxy;

	private final Directive interceptProxy;

	DirectiveImpl(String name, Location location, Expression expression, DirectiveHandler directiveHandler, String prototype, List elementInterceptors) {
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.directiveHandler = directiveHandler;
		this.elementInterceptors = elementInterceptors;
		this.handleProxy = new DirectiveProxy(this);
		this.interceptProxy = new DirectiveInterceptProxy(this);
	}

	public void render(Context context) throws RenderingException {
		if (elementInterceptors != null && elementInterceptors.size() > 0)
			new RenditionImpl(interceptProxy, context, elementInterceptors).doRender();
		else
			doRender(context);
	}

	void doRender(Context context) throws RenderingException {
		try {
			directiveHandler.doRender(context, handleProxy);
		} catch (RenderingException e) {
			throw e;
		} catch (IgnoreException e) {
			throw e;
		} catch (Exception e) {
			throw new RenderingException(this, e);
		}
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		Expression expression = getExpression();
		if (expression != null)
			expression.accept(visitor);
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public Expression getExpression() {
		return expression;
	}

	public String getSource() {
		return prototype;
	}

	public String getSignature() {
		return prototype;
	}

	private Template template;

	public Template getTemplate() {
		return template;
	}

	void setTemplate(Template template) {
		this.template = template;
	}

}
