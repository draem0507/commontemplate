package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.commontemplate.core.Source;

/**
 * 匿名模板源
 *
 * @author liangfei0201@163.com
 *
 */
final class SourceImpl extends Source implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_ENCODING = "UTF-8";

	private final String templateName;

	private final String template;

	private final String templateEncoding;

	private final long templateLastModified;

	SourceImpl(String template) {
		this(template, null, null, -1);
	}

	SourceImpl(String template, String templateName) {
		this(template, templateName, null, -1);
	}

	SourceImpl(String template, String templateName, String templateEncoding) {
		this(template, templateName, templateEncoding, -1);
	}

	SourceImpl(String template, String templateName, String templateEncoding, long templateLastModified) {
		this.template = (template == null ? "" : template);
		this.templateName = (templateName == null ? "" : templateName);
		this.templateEncoding = (templateEncoding == null ? DEFAULT_ENCODING : templateEncoding);
		this.templateLastModified = (templateLastModified < UNKOWN_MODIFIED ? UNKOWN_MODIFIED : templateLastModified);
	}

	public final String getEncoding() {
		return templateEncoding;
	}

	public final long getLastModified() {
		return templateLastModified;
	}

	public final String getName() {
		return templateName;
	}

	public final Reader getReader() throws IOException {
		return new StringReader(template);
	}

	public String getSource(int beginOffset, int endOffset) {
		if (beginOffset < 0)
			beginOffset = 0;
		if (endOffset > template.length())
			endOffset = template.length();
		if (endOffset <= beginOffset)
			return "";
		return template.substring(beginOffset, endOffset);
	}

	public String getLineSource(int beginLine, int endLine) {
		if (endLine <= beginLine)
			return "";
		int count = 0;
		int begin = 0;
		int end = 0;
		for (int i = 0, n = template.length(); i < n; i ++) {
			if (template.charAt(i) == '\n')
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

}