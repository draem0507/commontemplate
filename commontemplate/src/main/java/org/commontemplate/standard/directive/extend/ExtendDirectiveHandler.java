package org.commontemplate.standard.directive.extend;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.filter.IgnoreOutputFilter;
import org.commontemplate.core.Context;

/**
 * 模板继承指令
 *
 * @author liangfei0201@163.com
 *
 */
public class ExtendDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String EXTEND_INNER_PROPERTY = "extend_inner";

	protected void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		context.putProperty(EXTEND_INNER_PROPERTY, Boolean.TRUE);
		try {
			context.setOutputFilter(IgnoreOutputFilter.getInstance());
			DirectiveUtils.renderAll(innerElements, context);
			context.removeOutputFilter();
		} finally {
			context.removeProperty(EXTEND_INNER_PROPERTY);
		}
		context.putProperty(EXTEND_INNER_PROPERTY, Boolean.FALSE);
		try {
			context.getTemplate((String)param).render(context);
		} finally {
			context.removeProperty(EXTEND_INNER_PROPERTY);
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}
