package org.commontemplate.standard.directive.filter.escape;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

/**
 * 换行符转义过滤器.
 * "\n"换行符转为"&lt;br/&gt;"
 *
 * @author liangfei0201@163.com
 *
 */
public class NewlineEscapeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		if (message == null)
			return null;
		return message.replaceAll("\r?\n", "<br/>$0");
	}

}