package org.commontemplate.standard.property.object;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.XmlUtils;

public class ObjectToXstreamPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return XmlUtils.toXStream(bean);
	}

}