package org.commontemplate.engine.template;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Text;
import org.commontemplate.core.Visitor;
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

	public void accept(Visitor visitor) {
		text.accept(visitor);
	}

	public boolean equals(Object obj) {
		return text.equals(obj);
	}

	public String getCanonicalForm() {
		return text.getCanonicalForm();
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
		text.doRender(context); // 绕过拦截器
	}

}
