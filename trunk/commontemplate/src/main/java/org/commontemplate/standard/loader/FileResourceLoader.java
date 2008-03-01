package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;

import org.commontemplate.core.Resource;

/**
 * 系统磁盘模板源加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public class FileResourceLoader extends AbstractResourceLoader {

	public Resource loadResource(String path, String name, String encoding)
			throws IOException {
		return new FileResource(new File(path), name, encoding);
	}

}
