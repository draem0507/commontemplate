package org.commontemplate.standard.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.commontemplate.core.Resource;

public abstract class AbstractResource extends Resource implements java.io.Serializable {
	
	private final String name;

	private final String encoding;

	public AbstractResource(String name, String encoding) {
		this.name = name;
		this.encoding = encoding;
	}

	public String getName() {
		return name;
	}

	public String getEncoding() {
		return encoding;
	}

	public Reader getReader() throws IOException {
		if (encoding != null)
			return new InputStreamReader(getInputStream(), encoding);
		else
			return new InputStreamReader(getInputStream());
	}

	protected abstract InputStream getInputStream() throws IOException;

}
