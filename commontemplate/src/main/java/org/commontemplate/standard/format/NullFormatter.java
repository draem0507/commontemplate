package org.commontemplate.standard.format;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;

/**
 * Null值显示控制
 * 
 * @author liangfei0201@163.com
 *
 */
public class NullFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;

	public NullFormatter(String value) {
		this.value = value;
	}

	public String format(Object model, Locale locale, TimeZone timeZone) {
		return value;
	}

}
