package org.commontemplate.standard.property.date;

import java.util.Calendar;


public class WeekOfMonthHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public WeekOfMonthHandler(){
		super(Calendar.WEEK_OF_MONTH);
	}
	
}