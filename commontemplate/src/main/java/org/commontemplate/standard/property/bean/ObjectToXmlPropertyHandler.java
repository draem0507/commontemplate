package org.commontemplate.standard.property.bean;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ObjectToXmlPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		XMLEncoder xe = new XMLEncoder(bo);
		try {
			xe.writeObject(bean);
			xe.flush();
		} finally {
			xe.close();
		}
		return new String(bo.toByteArray());
	}

}