package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public abstract class DateFieldHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;
	
	private final int field;
	
	public DateFieldHandler(int field) {
		this.field = field;
	}
	
	public Object doProperty(Object bean) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date)bean);
		return new Integer(calendar.get(field));
	}

}
