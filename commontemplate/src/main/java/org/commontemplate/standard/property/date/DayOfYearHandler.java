package org.commontemplate.standard.property.date;

import java.util.Calendar;

public class DayOfYearHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public DayOfYearHandler(){
		super(Calendar.DAY_OF_YEAR);
	}
	
}