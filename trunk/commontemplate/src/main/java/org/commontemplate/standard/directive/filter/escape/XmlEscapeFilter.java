package org.commontemplate.standard.directive.filter.escape;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;
import org.commontemplate.util.coder.XML;

/**
 * XML转义过滤器.
 *
 * @author liangfei0201@163.com
 *
 */
public class XmlEscapeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		return XML.encode(message);
	}

}
