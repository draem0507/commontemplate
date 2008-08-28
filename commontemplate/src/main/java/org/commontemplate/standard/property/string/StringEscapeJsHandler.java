package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.coder.JavaScript;

/**
 * 字符串JS特殊符转义
 *
 * @author 陈志强
 * @since 2008-07-02
 *
 */
public class StringEscapeJsHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
        return JavaScript.encode((String)bean);
	}

}