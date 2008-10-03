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
public class ZipSourceLoader extends AbstractSourceLoader {
	
	private File zipFile; 
	
	public ZipSourceLoader() {
		
	}
	
	public ZipSourceLoader(String zipFilePath) throws IOException {
		this(new File(zipFilePath));
	}
	
	public ZipSourceLoader(File zipFile) throws IOException {
		this.zipFile = zipFile;
	}
	
	public void setZipFilePath(String zipFilePath) {
		this.zipFile = new File(zipFilePath);
	}

	public Source loadResource(String path, String name, String encoding)
			throws IOException {
		return new ZipSource(zipFile, name, encoding);
	}

}
