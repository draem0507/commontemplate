package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
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

	private final BlockDirectiveHandler blockDirectiveHandler;

	private final String endPrototype;

	private final List renderInterceptors;

	private final BlockDirective proxy;

	BlockDirectiveImpl(String name, Location location, Expression expression, BlockDirectiveHandler blockDirectiveHandler, String prototype, String endPrototype, List renderInterceptors) {
		Assert.assertNotNull(blockDirectiveHandler);
		if (blockDirectiveHandler.isExpressionRequired() && expression == null)
			throw I18nExceptionFactory.createIllegalStateException("BlockDirectiveImpl.expression.is.null", new Object[]{name});
		this.name = name;
		this.prototype = prototype;
		this.location = location;
		this.expression = expression;
		this.blockDirectiveHandler = blockDirectiveHandler;
		this.endPrototype = endPrototype;
		this.renderInterceptors = renderInterceptors;
		this.proxy = new BlockDirectiveProxy(this);
	}

	public void render(Context context) throws RenderingException {
		if (renderInterceptors != null && renderInterceptors.size() > 0)
			new RenditionImpl(proxy, context, renderInterceptors).doRender();
		else
			doRender(context);
	}

	void doRender(Context context) throws RenderingException {
		context.pushLocalContext(name);
		try {
			blockDirectiveHandler.doRender(context, proxy);
		} catch (RenderingException e) {
			throw e;
		} catch (IgnoreException e) {
			throw e;
		} catch (Exception e) {
			throw new RenderingException(this, context, e);
		} finally {
			context.popLocalContext();
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

	public String getCanonicalForm() throws IOException {
		return prototype + getElementsSource() + endPrototype;
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
		List directives = getElements();
		for (int i = 0, n = directives.size(); i < n; i ++) {
			Element directive = (Element)directives.get(i);
			if (directive instanceof BlockDirectiveImpl)
				((BlockDirectiveImpl)directive).setTemplate(template);
			else if (directive instanceof DirectiveImpl)
				((DirectiveImpl)directive).setTemplate(template);
			else if (directive instanceof TextImpl)
				((TextImpl)directive).setTemplate(template);
			else if (directive instanceof CommentImpl)
				((CommentImpl)directive).setTemplate(template);
		}
	}

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		try {
			visitor.visitBlockDirective(this);
			acceptElements(visitor);
			visitor.endBlockDirective(this);
		} catch (StopVisitException e) {
			if (! isEnter)
				throw e;
		}
	}

}