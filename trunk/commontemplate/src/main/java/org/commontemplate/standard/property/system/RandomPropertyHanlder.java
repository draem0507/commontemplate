package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;

public class RandomPropertyHanlder implements StaticPropertyHandler {

	public Object getProperty() throws Exception {
		return new Double(Math.random());
	}

}