package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.core.event.RenderedEvent;
import org.commontemplate.core.event.RenderingEvent;
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

	DirectiveImpl(String name, Location location, Expression expression, DirectiveHandler directiveHandler, String prototype, List elementInterceptors) {
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.directiveHandler = directiveHandler;
		this.elementInterceptors = elementInterceptors;
	}

	public void render(Context context) throws RenderingException {
		if (elementInterceptors != null && elementInterceptors.size() > 0)
			new ElementRenditionImpl(new DirectiveInterceptProxy(this), context, elementInterceptors).doRender();
		else
			doRender(context);
	}

	void doRender(Context context) throws RenderingException {
		context.publishEvent(new RenderingEvent(this));
		try {
			directiveHandler.doRender(context, new DirectiveProxy(this));
		} catch (RenderingException e) {
			throw e;
		} catch (IgnoreException e) {
			throw e;
		} catch (Exception e) {
			throw new RenderingException(this, e);
		} finally {
			context.publishEvent(new RenderedEvent(this));
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

	public String getCanonicalForm() {
		return prototype;
	}

	public String getSignature() {
		return prototype;
	}

}
