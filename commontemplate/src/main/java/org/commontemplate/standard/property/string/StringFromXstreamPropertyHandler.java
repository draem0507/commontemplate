package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

import com.thoughtworks.xstream.XStream;

public class StringFromXstreamPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return new XStream().fromXML((String)bean);
	}

}