package org.commontemplate.standard.property.object;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.JSONUtils;

/**
 * 对象转JSON数据格式串
 *
 * @author liangfei0201@163.com
 *
 */
public class ObjectToJsonPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return JSONUtils.toJson(bean);
	}

}