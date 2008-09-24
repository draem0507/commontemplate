package org.commontemplate.standard.property.system;

import java.util.Date;

import org.commontemplate.standard.property.StaticPropertyHandlerSupport;

public class NowPropertyHandler extends StaticPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty() throws Exception {
		return new Date();
	}

}
