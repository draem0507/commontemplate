package org.commontemplate.core;

import java.util.Locale;
import java.util.TimeZone;

/**
 * 对象输出格式化
 *
 * @see org.commontemplate.core.OutputController#setOutputFormatter(Class, OutputFormatter)
 * @see org.commontemplate.core.OutputController#setGeneralOutputFormatter(OutputFormatter)
 * @author liangfei0201@163.com
 *
 */
public interface OutputFormatter {

	/**
	 * 格式化数据模型为输出字符串
	 *
	 * @param model
	 *            被格式化的数据模型
	 * @param locale
	 *            地区信息
	 * @param timeZone
	 *            时区信息
	 * @return 输出字符串
	 * @throws UnformattedException 当前OutputFormatter未处理时抛出，将调用上级OutputFormatter处理
	 */
	public String format(Object model, Locale locale, TimeZone timeZone)
			throws UnformattedException;

}
