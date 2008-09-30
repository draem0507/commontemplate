package org.commontemplate.standard.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.commontemplate.util.Assert;

/**
 * URL模板源
 *
 * @author liangfei0201@163.com
 *
 */
public class URLResource extends AbstractSource {

	private static final long serialVersionUID = 1L;

	private final URL url;

	public URLResource(URL url, String name, String encoding) throws IOException {
		super(name, encoding);
		Assert.assertNotNull(url, "URLResource.url.required");
		this.url = url;
	}

	public long getLastModified() {
		try {
			return url.openConnection().getLastModified();
		} catch (IOException e) {
			return UNKOWN_MODIFIED;
		}
	}

	protected InputStream getInputStream() throws IOException {
		return url.openStream();
	}

}
