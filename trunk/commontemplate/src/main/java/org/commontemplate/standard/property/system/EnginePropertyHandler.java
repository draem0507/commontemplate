package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;

public class EnginePropertyHandler implements StaticPropertyHandler {
	
	private static final EngineBean engineBean = new EngineBean();
	
	public Object doProperty() throws Exception {
		return engineBean;
	}

}