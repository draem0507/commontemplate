package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarException;
import java.util.jar.JarFile;

import org.commontemplate.util.Assert;

public class JarResource extends AbstractResource {

	private static final long serialVersionUID = 1L;

	private final File file;

	public JarResource(File file, String name, String encoding) {
		super(name, encoding);
		Assert.assertNotNull(file, "JarResource.file.required");
		this.file = file;
	}

	protected InputStream getInputStream() throws IOException {
		JarFile jarFile = new JarFile(file);
		return jarFile.getInputStream(jarFile.getEntry(getName()));
	}

	public long getLastModified() {
		try {
			JarFile jarFile = new JarFile(file);
			return jarFile.getEntry(getName()).getTime();
		} catch (JarException e) {
			return -1;
		} catch (IOException e) {
			return -1;
		}
	}

}
