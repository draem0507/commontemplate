package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Expression;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.Location;

/**
 * 根指令
 *
 * @author liangfei0201@163.com
 *
 */
final class RootBlockDirectiveImpl extends BlockDirectiveSupport {

	private static final long serialVersionUID = 1L;

	RootBlockDirectiveImpl() {

	}

	public void render(Context context) throws RenderingException {
		try {
			List directives = getElements();
			for (int i = 0, n = directives.size(); i < n; i ++) {
				Element directive = (Element)directives.get(i);
				directive.render(context);
			}
		} catch (IgnoreException e) {
			// ignore
		}
	}

	public String getSource() throws IOException {
		return getElementsSource();
	}

	public void accept(TemplateVisitor visitor) {
		acceptElements(visitor);
	}

	public Expression getExpression() {
		return null;
	}

	public Location getLocation() {
		return Location.ZERO;
	}

	public String getName() {
		return "root";
	}

	public String getSignature() {
		return "";
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

}