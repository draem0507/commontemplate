package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.SystemPropertyHandlerSupport;
import org.commontemplate.util.coder.UUID;

public class UuidPropertyHandler extends SystemPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty() throws Exception {
		return UUID.randomUUID().toString();
	}

}
