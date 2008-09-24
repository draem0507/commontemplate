package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandlerSupport;

public class RandomPropertyHandler extends StaticPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty() throws Exception {
		return new Double(Math.random());
	}

}
