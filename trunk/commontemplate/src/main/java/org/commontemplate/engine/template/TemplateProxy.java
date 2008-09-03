package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.Location;

final class TemplateProxy extends Template {

	private static final long serialVersionUID = 1L;

	private TemplateImpl template;

	TemplateProxy(TemplateImpl template) {
		this.template = template;
	}

	TemplateImpl getTarget() {
		return template;
	}

	public void accept(TemplateVisitor visitor) {
		template.accept(visitor);
	}

	public boolean equals(Object obj) {
		return template.equals(obj);
	}

	public List getElements() {
		return template.getElements();
	}

	public String getEncoding() {
		return template.getEncoding();
	}

	public long getLastModified() {
		return template.getLastModified();
	}

	public long getLength() {
		return template.getLength();
	}

	public String getLineSource(int beginLine, int endLine) throws IOException {
		return template.getLineSource(beginLine, endLine);
	}

	public String getLineSource(Location location) throws IOException {
		return template.getLineSource(location);
	}

	public String getName() {
		return template.getName();
	}

	public Reader getReader() throws IOException {
		return template.getReader();
	}

	public String getSource() throws IOException {
		return template.getSource();
	}

	public String getSource(int beginOffset, int endOffset) throws IOException {
		return template.getSource(beginOffset, endOffset);
	}

	public String getSource(Location location) throws IOException {
		return template.getSource(location);
	}

	public String getType() {
		return template.getType();
	}

	public int hashCode() {
		return template.hashCode();
	}

	public void render(Context context) throws RenderingException {
		throw I18nExceptionFactory.createUnsupportedOperationException("TemplateProxy.cycle.render", new Object[]{getName()});
	}

	public String toString() {
		return template.toString();
	}

	public void accept(TemplateVisitor visitor, boolean isEnter) {
		template.accept(visitor, isEnter);
	}

}
