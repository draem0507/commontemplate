package org.commontemplate.standard.property.system;

import org.commontemplate.standard.property.SystemPropertyHandlerSupport;

/**
 * 引擎属性
 *
 * @author liangfei0201@163.com
 *
 */
public class EnginePropertyHandler extends SystemPropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final EngineBean engineBean = new EngineBean();

	public Object doProperty() throws Exception {
		return engineBean;
	}

}
