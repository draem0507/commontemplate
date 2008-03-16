package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;

public class SystemPropertyHanlder implements StaticPropertyHandler {
	
	private static final SystemBean systemBean = new SystemBean();
	
	public Object handleProperty() throws Exception {
		return systemBean;
	}
	
}