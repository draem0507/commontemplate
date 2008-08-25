package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.Text;
import org.commontemplate.util.Location;

/**
 * 文本块/不解释块元素实现
 *
 * @author liangfei0201@163.com
 *
 */
final class TextImpl extends Text {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Location location;

	private final String text;

	private final List elementInterceptors;

	private final Text proxy;

	TextImpl(String name, Location location, String text, List elementInterceptors) {
		this.name = name;
		this.location = location;
		this.text = text;
		this.elementInterceptors = elementInterceptors;
		this.proxy = new TextProxy(this);
	}

	public void render(Context context) throws RenderingException {
		if (elementInterceptors != null && elementInterceptors.size() > 0)
			new ElementRenditionImpl(proxy, context, elementInterceptors).doRender();
		else
			doRender(context);
	}

	void doRender(Context context) throws RenderingException {
		try {
			context.output(getValue());
		} catch (IOException e) {
			throw new RenderingException(this, e);
		}
	}

	public String getName() {
		return name;
	}

	public String getSource() throws IOException {
		return getValue();
	}

	public Location getLocation() {
		return location;
	}

	public String getValue() {
		return text;
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
	}

}
