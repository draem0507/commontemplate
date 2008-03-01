package org.commontemplate.standard.property.date;

import java.util.Calendar;

public class DateHourHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public DateHourHandler(){
		super(Calendar.HOUR_OF_DAY);
	}
	
}