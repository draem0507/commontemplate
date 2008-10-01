package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.SystemPropertyHandlerSupport;

public class JdkSystemPropertyHandler extends SystemPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final SystemBean systemBean = new SystemBean();

	public Object doProperty() throws Exception {
		return systemBean;
	}

}
