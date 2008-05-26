package org.commontemplate.standard.loader;

import java.io.IOException;
import java.io.Reader;

import org.commontemplate.core.Resource;
import org.commontemplate.util.Assert;

/**
 * 数据源模板资源
 *
 * @author liangfei0201@163.com
 *
 */
public class DataSourceResource extends Resource {

	private static final long serialVersionUID = 1L;

	private final Reader reader;

	private final long lastModified;

	private final String name;

	private final String encoding;

	public DataSourceResource(Reader reader, long lastModified, String name, String encoding) {
		Assert.assertNotNull(reader, "DataSourceResource.resource.reader.required");
		this.reader = reader;
		this.lastModified = lastModified;
		this.name = name;
		this.encoding = encoding;
	}

	public Reader getReader() throws IOException {
		return reader;
	}

	public long getLastModified() {
		return lastModified;
	}

	public String getName() {
		return name;
	}

	public String getEncoding() {
		return encoding;
	}

}