package org.commontemplate.standard.property.date;

import java.util.Calendar;

public class DateMillisecondHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public DateMillisecondHandler(){
		super(Calendar.MILLISECOND);
	}
	
}