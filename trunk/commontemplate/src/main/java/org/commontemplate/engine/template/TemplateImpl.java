package org.commontemplate.engine.template;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Assert;
import org.commontemplate.util.IOUtils;

/**
 * 模板实现
 * <p/>
 * (不变类，线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
final class TemplateImpl extends Template implements Serializable {

	private static final long serialVersionUID = 1L;

	private final BlockDirective rootDirective;

	private final String name;

	private final String encoding;

	private final long lastModified;

	private final char[] data;

	TemplateImpl(Resource resource, BlockDirective rootDirective) throws IOException {
		Assert.assertNotNull(resource, "resource == null");
		Assert.assertNotNull(rootDirective, "rootDirective == null");

		this.name = resource.getName();
		this.encoding = resource.getEncoding();
		this.lastModified = resource.getLastModified();
		this.data = IOUtils.readToChars(resource.getReader());
		this.rootDirective = rootDirective;
	}

	public final List getElements() {
		return rootDirective.getElements();
	}

	public final void render(Context context) throws RenderingException {
		context.pushTemplate(this);
		try {
			rootDirective.render(context);
		} catch (RenderingException e) {
			throw new RenderingException(this, context, e.getElement().getLocation(), e);
		} finally {
			context.popTemplate();
		}
	}

	public final void accept(Visitor visitor) {
		visitor.visit(this);
		rootDirective.accept(visitor);
	}

	public final String getEncoding() {
		return encoding;
	}

	public final long getLastModified() {
		return lastModified;
	}

	public final String getName() {
		return name;
	}

	public final Reader getReader() throws IOException {
		return new CharArrayReader(data);
	}

	public String getCanonicalForm() {
		return rootDirective.getCanonicalForm();
	}

	public String getOriginalText() {
		return null;
	}

}
