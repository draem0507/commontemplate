package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;

import org.commontemplate.core.Source;

/**
 * 系统磁盘模板源加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public class FileSourceLoader extends AbstractSourceLoader {

	public Source loadResource(String path, String name, String encoding)
			throws IOException {
		return new FileSource(new File(path), name, encoding);
	}

}
