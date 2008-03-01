package org.commontemplate.standard.format;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;

/**
 * 数字格式化器
 * 
 * @author liangfei0201@163.com
 *
 */
public class NumberFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private String format;

	public NumberFormatter(String format) {
		this.format = format;
	}

	public String format(Object model, Locale locale, TimeZone timeZone) {
		return new DecimalFormat(format).format(model); // DecimalFormat非线程安全, 所以每次均new实例.
	}

}
