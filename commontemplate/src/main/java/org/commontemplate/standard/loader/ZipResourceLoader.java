package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;

import org.commontemplate.core.Source;

/**
 * ZIP模板源加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ZipResourceLoader extends AbstractSourceLoader {
	
	private File zipFile; 
	
	public ZipResourceLoader() {
		
	}
	
	public ZipResourceLoader(String zipFilePath) throws IOException {
		this(new File(zipFilePath));
	}
	
	public ZipResourceLoader(File zipFile) throws IOException {
		this.zipFile = zipFile;
	}
	
	public void setZipFilePath(String zipFilePath) {
		this.zipFile = new File(zipFilePath);
	}

	public Source loadResource(String path, String name, String encoding)
			throws IOException {
		return new ZipResource(zipFile, name, encoding);
	}

}
