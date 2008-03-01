package org.commontemplate.standard.property.date;

import java.util.Calendar;

public class DateTimezoneHandler extends DateFieldHandler {

	private static final long serialVersionUID = 1L;

	public DateTimezoneHandler(){
		super(Calendar.ZONE_OFFSET);
	}
	
}