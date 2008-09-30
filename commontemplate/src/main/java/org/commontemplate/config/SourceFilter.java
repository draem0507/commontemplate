package org.commontemplate.config;

import java.io.IOException;
import java.io.Reader;

/**
 * 模板加载过滤器
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface SourceFilter {

	/**
	 * 过滤模板供给读取器
	 * 
	 * @param reader
	 *            过滤前的模板供给读取器
	 * @return 过滤后的模板供给读取器
	 * @throws IOException
	 */
	public Reader filter(Reader reader) throws IOException;

}
