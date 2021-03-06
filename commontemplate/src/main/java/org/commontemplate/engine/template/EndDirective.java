package org.commontemplate.engine.template;


import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.Location;

/**
 * 结束指令
 *
 * @author liangfei0201@163.com
 *
 */
final class EndDirective extends Directive {

	private static final long serialVersionUID = 1L;

	private final String blockDirectiveName;

	private final Location location;

	EndDirective(String blockDirectiveName, Location location) {
		this.blockDirectiveName = blockDirectiveName;
		this.location = location;
	}

	String getBlockDirectiveName() {
		return blockDirectiveName;
	}

	public void render(Context context) throws RenderingException {
		throw I18nExceptionFactory.createUnsupportedOperationException("EndDirective.unsupported.error");
	}

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		throw new UnsupportedOperationException("EndDirective.unsupported.error");
	}

	public Expression getExpression() {
		return null;
	}

	public String getSource() {
		return "$end";
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return "end";
	}

	public String getSignature() {
		return "$end";
	}

	public Template getTemplate() {
		return null;
	}

}
