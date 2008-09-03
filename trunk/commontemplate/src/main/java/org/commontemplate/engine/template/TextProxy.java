package org.commontemplate.engine.template;

import java.io.IOException;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.Text;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.Location;

/**
 * 文本块/不解释块元素代理
 *
 * @author liangfei0201@163.com
 *
 */
final class TextProxy extends Text {

	private static final long serialVersionUID = 3914348687967038468L;

	private final TextImpl text;

	TextProxy(TextImpl text) {
		this.text = text;
	}

	public void accept(TemplateVisitor visitor) {
		text.accept(visitor);
	}

	public boolean equals(Object obj) {
		return text.equals(obj);
	}

	public Template getTemplate() {
		return text.getTemplate();
	}

	public String getSource() throws IOException {
		return text.getSource();
	}

	public Location getLocation() {
		return text.getLocation();
	}

	public String getName() {
		return text.getName();
	}

	public String getValue() {
		return text.getValue();
	}

	public int hashCode() {
		return text.hashCode();
	}

	public String toString() {
		return text.toString();
	}

	public String getSignature() {
		return text.getSignature();
	}

	public void render(Context context) throws RenderingException {
		throw I18nExceptionFactory.createUnsupportedOperationException("TextProxy.cycle.render", new Object[]{getValue()});
	}

	TextImpl getTarget() {
		return text;
	}

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		text.accept(visitor, isEnter);
	}

}
