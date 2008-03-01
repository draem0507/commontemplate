package org.commontemplate.engine.template;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
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
final class LineDirective extends Directive {

	private static final long serialVersionUID = 1L;

	private final Expression expression;

	private final String name;

	private final Location location;

	private final String prototype;

	private final LineDirectiveHandler directiveHandler;
	
	LineDirective(String name, Location location, Expression expression, LineDirectiveHandler directiveHandler, String prototype) {
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.directiveHandler = directiveHandler;
	}

	public void render(Context context) throws RenderingException {
		context.publishEvent(new RenderingEvent(this));
		try {
			Expression expression = getExpression();
			Object param = (expression == null ? null : expression.evaluate(context));
			directiveHandler.doRender(context, getName(), param);
		} catch (RenderingException e) {
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

}
