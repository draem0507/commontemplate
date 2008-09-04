package org.commontemplate.tools.converter;

import java.io.Reader;
import java.io.Writer;

/**
 * 模板转换器接口
 *
 * @author liangfei0201@163.com
 *
 */
public interface TemplateConverter {

	/**
	 * 转换模板
	 *
	 * @param templateName 模板名称
	 * @param reader 原始模板内容读取器
	 * @param writer 转换后的CommonTemplate模板内容输出端
	 */
	void convert(String templateName, Reader reader, Writer writer) throws Exception;

}
