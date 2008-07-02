package org.commontemplate.standard.property.bean;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

import org.commontemplate.standard.property.PropertyHandlerSupport;

/**
 * 对象转XML数据格式串
 *
 * @author liangfei0201@163.com
 *
 */
public class ObjectToXmlPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
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