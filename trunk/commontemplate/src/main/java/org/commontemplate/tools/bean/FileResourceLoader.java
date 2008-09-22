package org.commontemplate.tools.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 文件资源加载器
 *
 * @author liangfei0201@163.com
 *
 */
public class FileResourceLoader implements ResourceLoader {

	public URL getResource(String name) throws IOException {
		return new File(name).toURI().toURL();
	}

	public InputStream getResourceAsStream(String name) throws IOException {
		return new FileInputStream(new File(name));
	}

}
