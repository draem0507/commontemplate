package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.FilteredVisitor;
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

	private final Directive proxy;

	DirectiveImpl(String name, Location location, Expression expression, DirectiveHandler directiveHandler, String prototype, List elementInterceptors) {
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.directiveHandler = directiveHandler;
		this.elementInterceptors = elementInterceptors;
		this.proxy = new DirectiveProxy(this);
	}

	public void render(Context context) throws RenderingException {
		if (elementInterceptors != null && elementInterceptors.size() > 0)
			new RenditionImpl(proxy, context, elementInterceptors).doRender();
		else
			doRender(context);
	}

	void doRender(Context context) throws RenderingException {
		try {
			directiveHandler.doRender(context, proxy);
		} catch (RenderingException e) {
			throw e;
		} catch (IgnoreException e) {
			throw e;
		} catch (Exception e) {
			throw new RenderingException(this, e);
		}
	}

	public void accept(Visitor visitor) {
		if (visitor instanceof FilteredVisitor
				&& ! ((FilteredVisitor)visitor).isVisit(this))
			return;
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

	public String getSource() throws IOException {
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
