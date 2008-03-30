package org.commontemplate.standard.property.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class DateToDateStringHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Object getProperty(Object bean) throws Exception {
		return dateFormat.format((Date)bean);
	}

}
