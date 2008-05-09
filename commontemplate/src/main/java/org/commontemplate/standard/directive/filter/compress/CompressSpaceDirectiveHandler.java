package org.commontemplate.standard.directive.filter.compress;


import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedFilter;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 空格压缩指令, 将连续的多空格压缩成单个.
 * 
 * @author liangfei0201@163.com
 *
 */
public class CompressSpaceDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;
	
	private static final SpaceFilter spaceFilter = new SpaceFilter();

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		context.output(spaceFilter.filter(bufferedFilter.getBuffered()));
	}

}

