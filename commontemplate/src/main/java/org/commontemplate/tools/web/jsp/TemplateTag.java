package org.commontemplate.tools.web.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.commontemplate.core.Context;
import org.commontemplate.tools.web.EngineHolder;

/**
 * 在JSP中内嵌CTL模板的Tag.
 * <p/>
 * 在JSP中：
 * <pre>
 * &lt;ct:template&gt;
 *     在此标签内写CTL, CTL可以从JSP上下文中取变量.
 *     如：
 *     $for{i : 1..5}
 *         ${i}
 *     $end
 * &lt;/ct:template&gt;
 * </pre>
 * 
 * @author liangfei0201@163.com
 *
 */
public class TemplateTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspException {
		Context context = getContext();
		if (context == null)
			throw new NullPointerException("context == null");
		try {
			EngineHolder.getEngine().parseTemplate(bodyContent.getString()).render(context);
		} finally {
			context.clear();
		}
		return EVAL_PAGE;
	}
	
	protected Context getContext() {
		Context context = EngineHolder.getEngine().createContext(pageContext.getOut(), ((HttpServletRequest)pageContext.getRequest()).getLocale());
		context.pushLocalContext("application", new JspMap(pageContext, PageContext.APPLICATION_SCOPE));
		context.pushLocalContext("session", new JspMap(pageContext, PageContext.SESSION_SCOPE));
		context.pushLocalContext("request", new JspMap(pageContext, PageContext.REQUEST_SCOPE));
		context.pushLocalContext("page", new JspMap(pageContext, PageContext.PAGE_SCOPE));
		return context;
	}

}
