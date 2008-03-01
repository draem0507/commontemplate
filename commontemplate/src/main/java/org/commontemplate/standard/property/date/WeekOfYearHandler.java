package org.commontemplate.standard.property.date;

import java.util.Calendar;


public class WeekOfYearHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public WeekOfYearHandler(){
		super(Calendar.WEEK_OF_YEAR);
	}
	
}