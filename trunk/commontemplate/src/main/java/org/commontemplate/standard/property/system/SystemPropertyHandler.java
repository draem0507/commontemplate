package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandlerSupport;

public class SystemPropertyHandler extends StaticPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final SystemBean systemBean = new SystemBean();

	public Object doProperty() throws Exception {
		return systemBean;
	}

}
