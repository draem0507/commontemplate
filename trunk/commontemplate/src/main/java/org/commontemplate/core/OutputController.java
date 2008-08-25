package org.commontemplate.core;

import java.io.IOException;

/**
 * 信息输出控制器
 *
 * @author liangfei0201@163.com
 *
 */
public interface OutputController {

	/**
	 * 设置输出内容转换器，用于output()时转换内容.
	 *
	 * @param outputConverter
	 *            输出内容转换器
	 */
	public void setOutputConverter(OutputConverter outputConverter);

	/**
	 * 获取当前LocalContext中的输出内容转换器
	 *
	 * @return 输出内容转换器
	 */
	public OutputConverter getOutputConverter();

	/**
	 * 移除当前上下文的转换器.
	 */
	public void removeOutputConverter();

	/**
	 * 设置输出内容过滤器，用于output()时过滤.
	 *
	 * @param outputFilter
	 *            输出内容过滤器
	 */
	public void setOutputFilter(OutputFilter outputFilter);

	/**
	 * 获取当前LocalContext中的输出内容过滤器
	 *
	 * @return 输出内容过滤器
	 */
	public OutputFilter getOutputFilter();

	/**
	 * 移除当前上下文的过滤器.
	 */
	public void removeOutputFilter();

	/**
	 * 设置指定类型内容格式化器，用于format()及output()时格式化.
	 *
	 * @param type
	 *            类型，为null时表示处理null值
	 * @param outputFormatter
	 *            输出内容格式化器
	 */
	public void setOutputFormatter(Class type, OutputFormatter outputFormatter);

	/**
	 * 获取当前LocalContext中的指定类型的格式化器
	 *
	 * @return 输出内容格式化器, 不存在时返回null.
	 */
	public OutputFormatter getOutputFormatter(Class type);

	/**
	 * 移除当前上下文中指定类型的格式化器.
	 */
	public void removeOutputFormatter(Class type);

	/**
	 * 设置通用格式化器，在未找到相关类型格式化器时使用，用于format()及output()时格式化.
	 *
	 * @param outputFormatter
	 *            输出内容格式化器
	 */
	public void setGeneralOutputFormatter(OutputFormatter outputFormatter);

	/**
	 * 获取当前LocalContext中的输出内容通用格式化器
	 *
	 * @return 输出内容通用格式化器
	 */
	public OutputFormatter getGeneralOutputFormatter();

	/**
	 * 移除当前上下文的通用格式化器.
	 */
	public void removeGeneralOutputFormatter();

	/**
	 * 清空当前上下文中的格式化器.
	 */
	public void clearOutputFormatters();

	/**
	 * 输出对象，非字符串对象将格式化成字符串输出.
	 *
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public void output(Object content) throws IOException;

	/**
	 * 格式化对象.
	 *
	 * @param content
	 *            对象源
	 * @return 格式化后的显示串
	 */
	public String format(Object content);

}
