package org.commontemplate.standard.loader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.commontemplate.core.Source;
import org.commontemplate.util.Assert;

/**
 * 字符串模板源
 *
 * @author liangfei0201@163.com
 *
 */
public class StringResource extends Source implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private final String source;

	private final String name;

	private final long lastModified;

	private static final String STRING_ENCODING = "UTF-8";

	public StringResource(String name, String source) {
		Assert.assertNotNull(source, "StringResource.template.source.required");
		this.source = source;
		this.name = name;
		this.lastModified = System.currentTimeMillis();
	}

	public String getName() {
		return name;
	}

	public String getEncoding() {
		return STRING_ENCODING;
	}

	public long getLastModified() {
		return lastModified;
	}

	public Reader getReader() throws IOException {
		return new StringReader(source);
	}

}
