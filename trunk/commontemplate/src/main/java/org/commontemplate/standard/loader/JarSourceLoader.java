package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;

import org.commontemplate.core.Source;

/**
 * 从Jar文件中加载模板
 * 
 * @author liangfei0201@163.com
 *
 */
public class JarSourceLoader extends AbstractSourceLoader {
	
	private File jarFile; 
	
	public JarSourceLoader() {
		
	}
	
	public JarSourceLoader(String jarFilePath) throws IOException {
		this(new File(jarFilePath));
	}
	
	public JarSourceLoader(File jarFile) throws IOException {
		this.jarFile = jarFile;
	}
	
	public void setJarFilePath(String jarFilePath) {
		this.jarFile = new File(jarFilePath);
	}

	public Source loadResource(String path, String name, String encoding)
			throws IOException {
		return new JarSource(jarFile, name, encoding);
	}

}
