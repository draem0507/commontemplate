package org.commontemplate.standard.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.commontemplate.util.Assert;

/**
 * 文件模板源
 *
 * @author liangfei0201@163.com
 *
 */
public class FileSource extends AbstractSource {

	private static final long serialVersionUID = 1L;

	private final File file;

	public FileSource(File file, String name, String encoding) {
		super(name, encoding);
		Assert.assertNotNull(file, "FileResource.file.required");
		this.file = file;
	}

	public long getLastModified() {
		return file.lastModified();
	}

	protected InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

}
