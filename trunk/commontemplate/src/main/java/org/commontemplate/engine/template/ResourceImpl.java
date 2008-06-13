package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.commontemplate.core.Resource;

/**
 * 匿名模板源
 *
 * @author liangfei0201@163.com
 *
 */
final class ResourceImpl extends Resource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private final String templateName;

	private final String template;

	ResourceImpl(String template) {
		this.template = template;
		this.templateName = "";
	}

	ResourceImpl(String template, String templateName) {
		this.template = template;
		this.templateName = templateName;
	}

	public final String getEncoding() {
		return "UTF-8";
	}

	public final long getLastModified() {
		return UNKOWN_MODIFIED;
	}

	public final String getName() {
		return templateName;
	}

	public final Reader getReader() throws IOException {
		return new StringReader(template);
	}

}