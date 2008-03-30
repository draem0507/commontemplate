package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;

public class EnginePropertyHanlder implements StaticPropertyHandler {
	
	private static final EngineBean engineBean = new EngineBean();
	
	public Object getProperty() throws Exception {
		return engineBean;
	}

}
