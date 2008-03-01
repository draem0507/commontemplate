package org.commontemplate.standard.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.util.Assert;

/**
 * 多模板源组合器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ResourceLoaderChain implements ResourceLoader {
	
	private List resourceLoaders;

	public void setResourceLoaders(List resourceLoaders) {
		Assert.assertNotEmpty(resourceLoaders, "模板加载器列表不能为空！");
		this.resourceLoaders = resourceLoaders;
	}
	
	public void addResourceLoader(ResourceLoader resourceLoader) {
		if (this.resourceLoaders == null)
			this.resourceLoaders = new ArrayList();
		this.resourceLoaders.add(resourceLoader);
	}

	public Resource loadResource(String name, String encoding)
			throws IOException {
		for (Iterator iterator = resourceLoaders.iterator(); iterator.hasNext();) {
			try {
				ResourceLoader resourceLoader = (ResourceLoader)iterator.next();
				Resource resource = resourceLoader.loadResource(name, encoding);
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// 忽略，继续取下一loader
			}
		}
		throw new IOException("无法找到模板源：" + name);
	}

	public Resource loadResource(String name) throws IOException {
		for (Iterator iterator = resourceLoaders.iterator(); iterator.hasNext();) {
			try {
				ResourceLoader resourceLoader = (ResourceLoader)iterator.next();
				Resource resource = resourceLoader.loadResource(name);
				if (resource != null)
					return resource;
			} catch (IOException e) {
				// 忽略，继续取下一loader
			}
		}
		throw new IOException("无法找到模板源：" + name);
	}
	
	public String toString() {
		return resourceLoaders.toString();
	}

}
