package org.commontemplate.standard.directive.taglib;

import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.Tag;

import org.commontemplate.core.Context;
import org.commontemplate.core.IgnoreException;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.ParameterUtils;
import org.commontemplate.tools.web.WebContext;
import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;

/**
 * JSP标签行指令适配器
 *
 * @author liangfei0201@163.com
 *
 */
public class TagLineDirectiveAdapter extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Class tagClass;

	public void setTagClassName(String tagClassName) {
		try {
			this.tagClass = ClassUtils.forName(tagClassName);
			Assert.assertTrue(JspTag.class.isAssignableFrom(tagClass), "TagLineDirectiveAdapter.jsp.tag.type.error");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void doRender(Context context, String directiveName, Object param)
			throws Exception {
		JspTag tag = (JspTag)tagClass.newInstance();
		if (tag instanceof Tag)
			doTag((Tag)tag, param, (WebContext)context);
		else if (tag instanceof SimpleTag)
			doSimpleTag((SimpleTag)tag, param, (WebContext)context);
	}

	private void doTag(Tag tag, Object param, WebContext context) throws Exception {
		try {
			// ---- init ----
			PageContextImpl pageContext = new PageContextImpl(context);
			pageContext.pushBody(context.getOut());
			tag.setPageContext(pageContext);
			Tag parentTag = (Tag)pageContext.findParentTag(Tag.class);
			if (parentTag != null)
				tag.setParent(parentTag);
			TagUtils.initTag(tag, ParameterUtils.getParameters(param));

			// ---- start ----
			int s = tag.doStartTag();
			if (s == Tag.EVAL_BODY_INCLUDE ||
					(s == BodyTag.EVAL_BODY_BUFFERED && tag instanceof BodyTag)) {
				// Ignore
			} else if (s != Tag.SKIP_BODY) {
				Assert.fail("TagLineDirectiveAdapter.start.tag.illegal.return.value", new Object[]{new Integer(s), tag.getClass().getName()});
	        }

			// ---- end ----
			int e = tag.doEndTag();
			if (e == Tag.SKIP_PAGE) {
				throw new IgnoreException();
			} else if (e != Tag.EVAL_PAGE) {
				Assert.fail("TagLineDirectiveAdapter.end.tag.illegal.return.value", new Object[]{new Integer(s), tag.getClass().getName()});
	        }
		} finally {
			// ---- release ----
			tag.release();
		}
	}

	// JSP2.0
	private void doSimpleTag(SimpleTag tag, Object param, WebContext context) throws Exception {
		PageContextImpl pageContext = new PageContextImpl(context);
		tag.setJspContext(pageContext);
        JspTag parentTag = (JspTag)pageContext.findParentTag(JspTag.class);
        if(parentTag != null) {
            tag.setParent(parentTag);
        }
        TagUtils.initTag(tag, ParameterUtils.getParameters(param));
        tag.doTag();
	}

}