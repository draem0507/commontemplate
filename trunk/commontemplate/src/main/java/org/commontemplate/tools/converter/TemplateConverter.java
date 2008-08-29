package org.commontemplate.tools.converter;

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
	 * @return 转换后的CommonTemplate模板内容输出端
	 */
	String convert(String source);

}
