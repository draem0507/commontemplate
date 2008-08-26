package org.commontemplate.standard.format;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.UnformattedException;
import org.commontemplate.util.StringCastUtils;

/**
 * 数组格式化器
 *
 * @author liangfei0201@163.com
 *
 */
public class ArrayFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = -3541910931467990145L;

	private final String arraySeparator;

	public ArrayFormatter(String arraySeparator) {
		this.arraySeparator = arraySeparator;
	}

	public String format(Object model, Locale locale, TimeZone timeZone)
			throws UnformattedException {
		return "[" + StringCastUtils.arrayToString(model, arraySeparator) + "]";
	}

}
