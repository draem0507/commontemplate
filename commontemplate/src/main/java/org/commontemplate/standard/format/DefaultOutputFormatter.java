package org.commontemplate.standard.format;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.UnformattedException;

public class DefaultOutputFormatter implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private OutputFormatter dateFormatter;

	public void setDateFormat(String format) {
		this.dateFormatter = new DateFormatter(format);
	}
	
	private OutputFormatter numberFormatter;
	
	public void setNumberFormat(String format) {
		this.numberFormatter = new NumberFormatter(format);
	}
	
	private OutputFormatter nullFormatter;
	
	public void setNullValue(String value) {
		this.nullFormatter = new NullFormatter(value);
	}
	
	private OutputFormatter arrayFormatter;

	public void setArraySeparator(String separator) {
		this.arrayFormatter = new ArrayFormatter(separator);
	}
	
	public String format(Object model, Locale locale, TimeZone timeZone) throws UnformattedException {
		if (model == null && nullFormatter != null) {
			return nullFormatter.format(null, locale, timeZone);
		} else if (model instanceof Number && numberFormatter != null) {
			return numberFormatter.format(model, locale, timeZone);
		} else if (model instanceof Date && dateFormatter != null) {
			return dateFormatter.format(model, locale, timeZone);
		} else if (model != null && model.getClass().isArray() && arrayFormatter != null) {
			return arrayFormatter.format(model, locale, timeZone);
		}
		throw new UnformattedException();
	}

}
