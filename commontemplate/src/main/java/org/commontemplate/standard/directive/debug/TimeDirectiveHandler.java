package org.commontemplate.standard.directive.debug;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.Context;

/**
 * 性能测试指令, 计算其内部块的执行时间.
 * 
 * @author liangfei0201@163.com
 *
 */
public class TimeDirectiveHandler implements BlockDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		long beginTime = System.currentTimeMillis();
		DirectiveUtils.renderAll(innerElements, context);
		context.getRootLocalContext().defineReadonlyVariable((String)param, new Long(System.currentTimeMillis() - beginTime));
	}
	
}
