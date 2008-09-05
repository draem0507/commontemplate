package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.StopVisitException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.Assert;
import org.commontemplate.util.I18nExceptionFactory;
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

	private final List renderInterceptors;

	private final Directive proxy;

	DirectiveImpl(String name, Location location, Expression expression, DirectiveHandler directiveHandler, String prototype, List renderInterceptors) {
		Assert.assertNotNull(directiveHandler);
		if (directiveHandler.isExpressionRequired() && expression == null)
			throw I18nExceptionFactory.createIllegalStateException("DirectiveImpl.expression.is.null", new Object[]{name});
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.directiveHandler = directiveHandler;
		this.renderInterceptors = renderInterceptors;
		this.proxy = new DirectiveProxy(this);
	}

	public void render(Context context) throws RenderingException {
		if (renderInterceptors != null && renderInterceptors.size() > 0)
			new RenditionImpl(proxy, context, renderInterceptors).doRender();
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
			throw new RenderingException(this, context, e);
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

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		try {
			visitor.visitDirective(this);
		} catch (StopVisitException e) {
			if (! isEnter)
				throw e;
		}
	}

}
