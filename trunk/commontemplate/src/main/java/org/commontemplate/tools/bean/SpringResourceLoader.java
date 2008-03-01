package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.core.io.Resource;

/**
 * Spring的Resource接口适配
 * 
 * @author liangfei0201@163.com
 *
 */
public class SpringResourceLoader implements ResourceLoader {
	
	private final Resource resource;
	
	public SpringResourceLoader(Resource resource) {
		this.resource = resource;
	}

	public URL getResource(String name) throws IOException {
		return resource.getURL();
	}

	public InputStream getResourceAsStream(String name) throws IOException {
		return resource.getInputStream();
	}

}
