package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;

import org.commontemplate.core.Resource;

/**
 * 从Jar文件中加载模板
 * 
 * @author liangfei0201@163.com
 *
 */
public class JarResourceLoader extends AbstractResourceLoader {
	
	private File jarFile; 
	
	public JarResourceLoader() {
		
	}
	
	public JarResourceLoader(String jarFilePath) throws IOException {
		this(new File(jarFilePath));
	}
	
	public JarResourceLoader(File jarFile) throws IOException {
		this.jarFile = jarFile;
	}
	
	public void setJarFilePath(String jarFilePath) {
		this.jarFile = new File(jarFilePath);
	}

	public Resource loadResource(String path, String name, String encoding)
			throws IOException {
		return new JarResource(jarFile, name, encoding);
	}

}
