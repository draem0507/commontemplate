package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.SystemPropertyHandlerSupport;

public class RandomPropertyHandler extends SystemPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty() throws Exception {
		return new Double(Math.random());
	}

}
