package org.commontemplate.ext.directive.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TestTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspException {
		try {
			super.pageContext.getOut().write("okok!");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

}
