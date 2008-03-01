package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.commontemplate.util.Assert;

/**
 * Class的ResourceLoader适配器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ClassResourceLoader implements ResourceLoader {
	
	private final Class clazz;
	
	public ClassResourceLoader() {
		clazz = ClassResourceLoader.class;
	}
	
	public ClassResourceLoader(final Class clazz) {
		Assert.assertNotNull(clazz, "class 不能为空！");
		this.clazz = clazz;
	}

	public InputStream getResourceAsStream(final String name) throws IOException {
		return clazz.getResourceAsStream(name);
	}
	
	public URL getResource(final String name) throws IOException {
		return clazz.getResource(name);
	}

}
