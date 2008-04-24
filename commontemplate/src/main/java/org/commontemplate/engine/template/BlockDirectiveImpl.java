package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.core.event.RenderedEvent;
import org.commontemplate.core.event.RenderingEvent;
import org.commontemplate.util.Location;

/**
 * 开始块指令
 *
 * @author liangfei0201@163.com
 *
 */
class BlockDirectiveImpl extends BlockDirectiveSupport {

	private static final long serialVersionUID = 1L;

	private final Expression expression;

	private final String name;

	private final Location location;

	private final String prototype;

	private final BlockDirectiveHandler startDirectiveHandler;

	private final String endPrototype;

	private final List elementInterceptors;

	BlockDirectiveImpl(String name, Location location, Expression expression, BlockDirectiveHandler startDirectiveHandler, String prototype, String endPrototype, List elementInterceptors) {
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.startDirectiveHandler = startDirectiveHandler;
		this.endPrototype = endPrototype;
		this.elementInterceptors = elementInterceptors;
	}

	public void render(Context context) throws RenderingException {
		if (elementInterceptors != null && elementInterceptors.size() > 0)
			new ElementRenditionImpl(new BlockDirectiveProxy(this), context, elementInterceptors).doRender();
		else
			doRender(context);
	}

	void doRender(Context context) throws RenderingException {
		context.publishEvent(new RenderingEvent(this));
		try {
			context.pushLocalContext();
			try {
				try {
					Expression expression = getExpression();
					Object param = (expression == null ? null : expression.evaluate(context));
					startDirectiveHandler.doRender(context, getName(), param, getElements());
				} catch (RenderingException e) {
					throw e;
				} catch (IgnoreException e) {
					throw e;
				} catch (Exception e) {
					throw new RenderingException(this, e);
				}
			} finally {
				context.popLocalContext();
			}
		} finally {
			context.publishEvent(new RenderedEvent(this));
		}
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
		return prototype + getCanonicalFormAll() + endPrototype;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		acceptExpression(visitor);
		acceptAll(visitor);
	}

	public String getSignature() {
		return prototype;
	}

}