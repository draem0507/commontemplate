package org.commontemplate.standard.directive.data;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedFilter;

public class DataDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Map dataProviders;

	public void setDataProviders(Map dataProviders) {
		this.dataProviders = dataProviders;
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param == null)
			throw new RuntimeException("$data指令参数不能为空, 必需为String");
		if (! (param instanceof String))
			throw new RuntimeException("$data指令参数必需为String");
		if (dataProviders == null)
			throw new RuntimeException("没有注册dataProviders");
		DataProvider dataProvider = (DataProvider)dataProviders.get((String)param);
		if (dataProvider == null)
			throw new RuntimeException("$data指令不支持:" + param
					+ "数据类型. 配置中只支持数据类型: " + dataProviders.keySet()
					+ ", 可以通过实现:"+DataProvider.class.getName()+"接口扩展数据供给类型. 然后在commontemplate.properties中注册, 如: dataProvider{xxx}=com.xxx.XXXDataProvider()");
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String dataSource = bufferedFilter.getBuffered();
		Map data = dataProvider.getData(dataSource);
		context.getSuperLocalContext().putAllVariables(data);
	}

}