package org.commontemplate.standard.directive.block;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;

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
		Assert.assertNotNull(param, "BlockDefineDirectiveHandler.parameter.required");
		Assert.assertTrue(param instanceof String, "BlockDefineDirectiveHandler.parameter.type.error");
		String var = (String)param;
		context.getParentLocalContext().putProperty(BLOCK_TYPE, var, innerElements);
	}

}
