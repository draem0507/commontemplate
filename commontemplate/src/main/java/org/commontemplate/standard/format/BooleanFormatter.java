package org.commontemplate.standard.format;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;

public class BooleanFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;

	private String trueValue;

	private String falseValue;

	public BooleanFormatter(String value) {
		String[] tokens = value.split("\\|");
		this.trueValue = tokens[0];
		this.trueValue = tokens[1];
	}

	public BooleanFormatter(String trueValue, String falseValue) {
		this.trueValue = trueValue;
		this.falseValue = falseValue;
	}

	public String format(Object model, Locale locale, TimeZone timeZone) {
		return ((Boolean)model).booleanValue() ? trueValue : falseValue;
	}

}