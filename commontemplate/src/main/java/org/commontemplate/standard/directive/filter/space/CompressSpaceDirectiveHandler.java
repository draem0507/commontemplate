package org.commontemplate.standard.directive.filter.space;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedOutputFilter;

/**
 * 空格压缩指令, 将连续的多空格压缩成单个.
 *
 * @author liangfei0201@163.com
 *
 */
public class CompressSpaceDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final OutputFilter spaceFilter = new CompressSpaceFilter();

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		BufferedOutputFilter bufferedFilter = new BufferedOutputFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		context.output(spaceFilter.filter(bufferedFilter.getBuffered()));
	}

}

