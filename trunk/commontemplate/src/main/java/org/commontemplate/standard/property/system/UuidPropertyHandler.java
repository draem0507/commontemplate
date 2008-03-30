package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;
import org.commontemplate.util.coder.UUID;

public class UuidPropertyHandler implements StaticPropertyHandler {

	public Object getProperty() throws Exception {
		return UUID.randomUUID();
	}

}
