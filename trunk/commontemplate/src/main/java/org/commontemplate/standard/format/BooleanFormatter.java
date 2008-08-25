package org.commontemplate.standard.format;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;
import org.commontemplate.util.Assert;

public class BooleanFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;

	private String trueValue;

	private String falseValue;

	public BooleanFormatter(String value) {
		String[] tokens = value.split("\\|");
		Assert.assertTrue(tokens != null && tokens.length == 2, "错误的配置项：defaultBooleanValue=" + value + "，应该为：defaultBooleanValue=true|false");
		this.trueValue = tokens[0];
		this.falseValue = tokens[1];
	}

	public BooleanFormatter(String trueValue, String falseValue) {
		this.trueValue = trueValue;
		this.falseValue = falseValue;
	}

	public String format(Object model, Locale locale, TimeZone timeZone) {
		return ((Boolean)model).booleanValue() ? trueValue : falseValue;
	}

}