package org.commontemplate.standard.property.object;

import org.commontemplate.standard.property.PropertyHandlerSupport;

import com.thoughtworks.xstream.XStream;

public class ObjectToXstreamPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return new XStream().toXML(bean);
	}

}