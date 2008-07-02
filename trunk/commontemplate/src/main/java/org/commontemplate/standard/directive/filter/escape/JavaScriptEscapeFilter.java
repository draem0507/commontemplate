package org.commontemplate.standard.directive.filter.escape;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;
import org.commontemplate.util.JavaScriptUtils;

/**
 * 文本块JS特殊符转义
 *
 * @author 陈志强
 * @since 2008-07-02
 *
 */
public class JavaScriptEscapeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		return JavaScriptUtils.javaScriptEscape(message);
	}

}