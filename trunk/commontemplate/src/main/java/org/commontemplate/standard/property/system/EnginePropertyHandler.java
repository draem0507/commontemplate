package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.StaticPropertyHandler;

/**
 * 引擎属性
 *
 * @author liangfei0201@163.com
 *
 */
public class EnginePropertyHandler implements StaticPropertyHandler {

	private static final EngineBean engineBean = new EngineBean();

	public Object doProperty() throws Exception {
		return engineBean;
	}

}
