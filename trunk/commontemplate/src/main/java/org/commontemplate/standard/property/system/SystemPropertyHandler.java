package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;

public class SystemPropertyHandler implements StaticPropertyHandler {
	
	private static final SystemBean systemBean = new SystemBean();
	
	public Object doProperty() throws Exception {
		return systemBean;
	}
	
}
