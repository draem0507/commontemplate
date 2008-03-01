package org.commontemplate.standard.format;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;

/**
 * 日期格式化器
 * 
 * @author liangfei0201@163.com
 *
 */
public class DateFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private String format;

	public DateFormatter(String format) {
		this.format = format;
	}

	public String format(Object model, Locale locale, TimeZone timeZone) {
		return new SimpleDateFormat(format).format(model); // SimpleDateFormat非线程安全, 所以每次均new实例.
	}

}
