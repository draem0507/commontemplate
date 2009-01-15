package org.commontemplate.standard.property.object;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.XmlUtils;

/**
 * 对象转XML数据格式串
 *
 * @author liangfei0201@163.com
 *
 */
public class ObjectToXmlPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return XmlUtils.toXml(bean);
	}

}