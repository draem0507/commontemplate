package org.commontemplate.standard.property.system;

import java.util.Date;

import org.commontemplate.standard.property.StaticPropertyHandler;

public class NowPropertyHanlder implements StaticPropertyHandler {

	public Object getProperty() throws Exception {
		return new Date();
	}

}
