package org.commontemplate.standard.property.date;

import java.util.Calendar;

public class DateYearHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public DateYearHandler(){
		super(Calendar.YEAR);
	}
	
}