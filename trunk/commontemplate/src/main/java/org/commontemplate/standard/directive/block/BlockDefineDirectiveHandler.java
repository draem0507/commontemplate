package org.commontemplate.standard.directive.block;

import java.util.List;

import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 模板块定义. 用于传递.
 *
 * @author liangfei0201@163.com
 *
 */
public class BlockDefineDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String BLOCK_TYPE = "block";

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param == null)
			throw new java.lang.IllegalArgumentException("$block 指令必需指定块定义名称! 如: $block{\"blockName\"}");
		String var = (String)param;
		context.getSuperLocalContext().putObject(BLOCK_TYPE, var, innerElements);
	}

}
