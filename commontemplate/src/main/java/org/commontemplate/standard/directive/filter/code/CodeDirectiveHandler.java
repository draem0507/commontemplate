package org.commontemplate.standard.directive.filter.code;

import java.util.List;
import java.util.Map;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedOutputFilter;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.OutputFilter;

/**
 * 代码着色高亮显示过滤(HTML)
 *
 * @author liangfei0201@163.com
 *
 */
public class CodeDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Map filters;

	public void setFilters(Map filters) {
		this.filters = filters;
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		String key = (String)param;
		if (filters.containsKey(key)) {
			BufferedOutputFilter bufferedFilter = new BufferedOutputFilter();
			context.setOutputFilter(bufferedFilter);
			DirectiveUtils.renderAll(innerElements, context);
			context.removeOutputFilter();
			context.output(((OutputFilter)filters.get(key)).filter(bufferedFilter.getBuffered()));
		} else {
			DirectiveUtils.renderAll(innerElements, context);
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}

