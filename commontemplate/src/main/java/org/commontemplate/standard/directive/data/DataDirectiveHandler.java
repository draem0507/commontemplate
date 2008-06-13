package org.commontemplate.standard.directive.data;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedFilter;

public class DataDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param == null)
			throw new RuntimeException("$data指令参数不能为空, 必需为String");
		if (! (param instanceof String))
			throw new RuntimeException("$data指令参数必需为String");
		DataProvider dataProvider = DataProviderManager.getDataProvider((String)param);
		if (dataProvider == null)
			throw new RuntimeException("$data指令不支持:" + param + "数据格式. 只支持数据格式: " + DataProviderManager.getDataProviderNames());
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String dataSource = bufferedFilter.getBuffered();
		Map data = dataProvider.getData(dataSource);
		context.getSuperLocalContext().putAllVariables(data);
	}

}
