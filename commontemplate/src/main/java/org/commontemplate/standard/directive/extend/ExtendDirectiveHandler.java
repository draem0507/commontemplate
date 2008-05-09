package org.commontemplate.standard.directive.extend;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 模板继承指令
 * 
 * @author liangfei0201@163.com
 *
 */
public class ExtendDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		context.setOutputFilter(IgnoreOutputFilter.getInstance());
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		
		context.getTemplate((String)param).render(context);
	}

}
