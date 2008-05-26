package org.commontemplate.standard.directive.filter.escape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.OutputFilterChain;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.core.Context;
import org.commontemplate.core.OutputFilter;

/**
 * 过滤器指令, 用注册的过滤器进行过滤.
 *
 * @author liangfei0201@163.com
 *
 */
public class EscapeDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Map filters;

	public void setFilters(Map filters) {
		this.filters = filters;
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param instanceof String) {
			context.setOutputFilter((OutputFilter)filters.get((String)param));
			DirectiveUtils.renderAll(innerElements, context);
			context.removeOutputFilter();
		} else if (param instanceof List) {
			List list = (List)param;
			List chains = new ArrayList(list.size());
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				chains.add((OutputFilter)filters.get((String)iterator.next()));
			}
			context.setOutputFilter(new OutputFilterChain(chains));
			DirectiveUtils.renderAll(innerElements, context);
			context.removeOutputFilter();
		} else {
			Assert.fail("EscapeDirectiveHandler.parameter.error", new Object[]{param});
		}
	}

}
