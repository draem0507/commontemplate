package org.commontemplate.engine.template;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Source;
import org.commontemplate.core.StopVisitException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
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

	private final RootBlockDirectiveImpl rootDirective;

	private final String name;

	private final String encoding;

	private final long lastModified;

	private final char[] data;

	private final List renderInterceptors;

	private final Template proxy;

	TemplateImpl(Source source, RootBlockDirectiveImpl rootDirective, List renderInterceptors) throws IOException {
		this(source == null ? null : source.getReader(), source, rootDirective, renderInterceptors);
	}

	TemplateImpl(Reader reader, Source source, RootBlockDirectiveImpl rootDirective, List renderInterceptors) throws IOException {
		Assert.assertNotNull(source, "TemplateImpl.resource.required");
		Assert.assertNotNull(rootDirective, "TemplateImpl.elements.required");

		this.name = source.getName();
		this.encoding = source.getEncoding();
		this.lastModified = source.getLastModified();
		this.data = IOUtils.readToChars(reader);
		this.rootDirective = rootDirective;
		this.rootDirective.setTemplate(this);
		this.renderInterceptors = renderInterceptors;
		this.proxy = new TemplateProxy(this);
	}

	public final List getElements() {
		return rootDirective.getElements();
	}

	public final void render(Context context) throws RenderingException {
		if (renderInterceptors != null && renderInterceptors.size() > 0)
			new RenditionImpl(proxy, context, renderInterceptors).doRender();
		else
			doRender(context);
	}

	final void doRender(Context context) throws RenderingException {
		context.pushTemplate(this);
		try {
			context.pushLocalContext();
			try {
				rootDirective.render(context);
			} catch (IgnoreException e) {
				// ignore
			} finally {
				context.popLocalContext();
			}
		} finally {
			context.popTemplate();
		}
	}

	public final void accept(TemplateVisitor visitor, boolean isEnter) {
		try {
			visitor.visitTemplate(this);
			rootDirective.accept(visitor, false);
			visitor.endTemplate(this);
		} catch (StopVisitException e) {
			if (! isEnter)
				throw e;
		}
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

	public final String getSource() {
		return new String(data);
	}

	public String getSource(int beginIndex, int endIndex) {
		if (beginIndex < 0)
			beginIndex = 0;
		if (endIndex > data.length)
			endIndex = data.length;
		if (endIndex <= beginIndex)
			return "";
		int len = endIndex - beginIndex;
		char[] block = new char[len];
		System.arraycopy(data, beginIndex, block, 0, len);
		return new String(block);
	}

	public String getLineSource(int beginLine, int endLine) {
		if (endLine <= beginLine)
			return "";
		int count = 0;
		int begin = 0;
		int end = 0;
		for (int i = 0, n = data.length; i < n; i ++) {
			if (data[i] == '\n')
				count ++;
			if (count == beginLine)
				begin = i;
			if (count == endLine) {
				end = i;
				break;
			}
		}
		return getSource(begin, end);
	}

	public long getLength() {
		return data.length;
	}

	// hashCode() and equals() generate by eclipse

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TemplateImpl other = (TemplateImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
