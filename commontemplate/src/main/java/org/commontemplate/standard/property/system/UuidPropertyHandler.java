package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandlerSupport;
import org.commontemplate.util.coder.UUID;

public class UuidPropertyHandler extends StaticPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty() throws Exception {
		return UUID.randomUUID().toString();
	}

}
