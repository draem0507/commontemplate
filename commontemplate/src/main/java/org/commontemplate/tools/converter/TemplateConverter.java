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
	 * @param source 原始模板内容读取器
	 * @param target 转换后的CommonTemplate模板内容输出端
	 */
	void convert(Reader source, Writer target);

}
