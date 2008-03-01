package org.commontemplate.standard.directive.taglib;

import java.io.Serializable;
import java.util.List;

import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.Tag;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.util.Assert;

/**
 * JSP标签块指令适配器
 * 
 * @author liangfei0201@163.com
 *
 */
public class TagBlockDirectiveAdapter implements BlockDirectiveHandler, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Class tagClass;

	public void setTagClassName(String tagClassName) {
		try {
			this.tagClass = Class.forName(tagClassName);
			Assert.assertTrue(JspTag.class.isAssignableFrom(tagClass), "必需为JspTag!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		JspTag tag = (JspTag)tagClass.newInstance();
		if (tag instanceof Tag)
			doTag((Tag)tag, param, innerElements, context);
		else if (tag instanceof SimpleTag)
			doSimpleTag((SimpleTag)tag, param, innerElements, context);
	}
	
	private void doTag(Tag tag, Object param,
			List elements, Context context) throws Exception {
		try {
			// ---- init ----
			PageContextImpl pageContext = new PageContextImpl(context);
			pageContext.pushBody(context.getOut());
			tag.setPageContext(pageContext);
			Tag parentTag = (Tag)pageContext.findParentTag(Tag.class);
			if (parentTag != null)
				tag.setParent(parentTag);
			TagUtils.initTag(tag, ParameterUtils.getParameters(param));
			pageContext.pushTag(tag);
			
			// ---- start ----
			int s = tag.doStartTag();
			if (s == Tag.EVAL_BODY_INCLUDE || 
					(s == BodyTag.EVAL_BODY_BUFFERED && tag instanceof BodyTag)) {
				// ---- body ----
				if (s == BodyTag.EVAL_BODY_BUFFERED && tag instanceof BodyTag) {
					BodyTag bodyTag = (BodyTag)tag;
					bodyTag.setBodyContent(new BodyContentImpl(pageContext.getOut(), false));
					bodyTag.doInitBody();
				}
				
				DirectiveUtils.renderAll(elements, context);
				
				// ---- iteration ----
				if (tag instanceof IterationTag) {
					IterationTag iterationTag = (IterationTag)tag;
					int a = iterationTag.doAfterBody();
					for (;;) {
						if (a == Tag.SKIP_BODY) {
							if (tag instanceof BodyTag)
								pageContext.popBody();
							break;
						} else if (a != IterationTag.EVAL_BODY_AGAIN)  {
				            throw new RuntimeException("Illegal return value " + s + " from " + tag.getClass().getName() + ".doAfterBody()");
				        }
						DirectiveUtils.renderAll(elements, context);
						a = iterationTag.doAfterBody();
					}
				}
			} else if (s != Tag.SKIP_BODY) {
	            throw new RuntimeException("Illegal return value " + s + " from " + tag.getClass().getName() + ".doStartTag()");
	        }
			
			// ---- end ----
			int e = tag.doEndTag();
			if (e == Tag.SKIP_PAGE) {
				throw new IgnoreException();
			} else if (e != Tag.EVAL_PAGE) {
	            throw new RuntimeException("Illegal return value " + s + " from " + tag.getClass().getName() + ".doEndTag()");
	        }
		} finally {
			// ---- release ----
			tag.release();
		}
	}
	
	// JSP2.0
	private void doSimpleTag(SimpleTag tag, Object param,
			List elements, Context context) throws Exception {
		PageContextImpl pageContext = new PageContextImpl(context);
		tag.setJspContext(pageContext);
        JspTag parentTag = (JspTag)pageContext.findParentTag(JspTag.class);
        if(parentTag != null)
            tag.setParent(parentTag);
        TagUtils.initTag(tag, ParameterUtils.getParameters(param));
        tag.setJspBody(new JspFragmentImpl(pageContext, context, null));
        pageContext.pushTag(tag);
        try {
            tag.doTag();
        } finally {
            pageContext.popTag();
        }
	}

}
