package org.commontemplate.standard.property.string;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringFromXmlPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		ByteArrayInputStream bi = new ByteArrayInputStream(((String)bean).getBytes());
		XMLDecoder xd = new XMLDecoder(bi);
		try {
			return xd.readObject();
		} finally {
			xd.close();
		}
	}

}