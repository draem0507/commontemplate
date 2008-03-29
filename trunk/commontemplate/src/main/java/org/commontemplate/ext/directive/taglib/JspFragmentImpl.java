package org.commontemplate.ext.directive.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveUtils;

/**
 * JspFragment适配实现
 * 
 * @author liangfei0201@163.com
 *
 */
class JspFragmentImpl extends JspFragment {
	
	private final JspContext jspContext;
	
	private final Context context;
	
	private final List elements;

	JspFragmentImpl(JspContext jspContext, Context context, List elements) {
		super();
		this.jspContext = jspContext;
		this.context = context;
		this.elements = elements;
	}

	public JspContext getJspContext() {
		return jspContext;
	}

	public void invoke(Writer out) throws JspException, IOException {
		DirectiveUtils.renderAll(elements, context);
	}

}
