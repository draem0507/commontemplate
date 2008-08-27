package org.commontemplate.standard.directive.filter.space;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedOutputFilter;

public class RightTrimSpaceDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final OutputFilter spaceFilter = new RightTrimSpaceFilter();

	protected void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		BufferedOutputFilter bufferedFilter = new BufferedOutputFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		context.output(spaceFilter.filter(bufferedFilter.getBuffered()));
	}

}
