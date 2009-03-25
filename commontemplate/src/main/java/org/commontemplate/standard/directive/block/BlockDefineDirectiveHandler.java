package org.commontemplate.standard.directive.block;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.VariableScopeUtils;
import org.commontemplate.standard.operator.string.NamePair;
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
		if (param instanceof String) {
			context.getParentLocalContext().putProperty(BLOCK_TYPE, (String)param, innerElements);
		} else if (param instanceof NamePair) {
			VariableScopeUtils.putVariable(context, true, (NamePair)param, innerElements);
		} else {
			Assert.fail("BlockDefineDirectiveHandler.parameter.type.error");
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
