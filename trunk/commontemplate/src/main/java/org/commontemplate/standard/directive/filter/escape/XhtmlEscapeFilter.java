package org.commontemplate.standard.directive.filter.escape;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;
import org.commontemplate.util.coder.XHTML;

/**
 * XHTML转义过滤器.
 * 类似XML转义过滤器, 但"&apos;" 改为 "&#39;"
 * 参见：<a href="http://www.w3.org/TR/xhtml1/#C_16">http://www.w3.org/TR/xhtml1/#C_16</a>
 *
 * @author liangfei0201@163.com
 *
 */
public class XhtmlEscapeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		return XHTML.encode(message);
	}

}
