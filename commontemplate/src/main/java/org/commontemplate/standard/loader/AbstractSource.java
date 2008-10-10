package org.commontemplate.standard.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.commontemplate.core.Source;
import org.commontemplate.util.Assert;

public abstract class AbstractSource extends Source implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String name;

	private final String encoding;

	public AbstractSource(String name, String encoding) {
		Assert.assertNotNull(name, "AbstractResource.resource.name.required");
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
