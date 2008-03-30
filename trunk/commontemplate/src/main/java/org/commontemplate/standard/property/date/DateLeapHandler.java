package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class DateLeapHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private final Calendar calendar = Calendar.getInstance();

	public Object getProperty(Object bean) throws Exception {
		calendar.setTime((Date)bean);
		int year = calendar.get(Calendar.YEAR);
		return Boolean.valueOf((year % 4) == 0);
	}

}