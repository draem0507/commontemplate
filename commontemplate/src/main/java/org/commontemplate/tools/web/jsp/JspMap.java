package org.commontemplate.tools.web.jsp;

import java.util.Enumeration;

import javax.servlet.jsp.PageContext;

import org.commontemplate.util.MapSupport;

/**
 * JSP属性Map封装
 * 
 * @author liangfei0201@163.com
 *
 */
public class JspMap extends MapSupport {

	private static final long serialVersionUID = 1L;

    private final PageContext pageContext;
    
    private final int scope;

    public JspMap(PageContext pageContext, int scope)  {
    	if (pageContext == null) 
			throw new java.lang.NullPointerException("pageContext is null!");
    	if (scope != PageContext.PAGE_SCOPE
    			&& scope != PageContext.REQUEST_SCOPE
    			&& scope != PageContext.SESSION_SCOPE
    			&& scope != PageContext.APPLICATION_SCOPE) 
    		throw new java.lang.IllegalArgumentException("Illegal Scope!");
        this.pageContext = pageContext;
        this.scope = scope;
    }

	protected Enumeration getNames() {
		return pageContext.getAttributeNamesInScope(scope); 
	}

	protected Object getValue(Object key) {
		return pageContext.getAttribute((String)key, scope);
	}

}
