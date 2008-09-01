package org.commontemplate.standard.directive.data;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.data.DataProvider;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedOutputFilter;

/**
 * 数据指令. 使用如:
 * $data{"xml"}
 * ...
 * $end
 *
 * @author liangfei0201@163.com
 *
 */
public class DataDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Map dataProviders;

	public void setDataProviders(Map dataProviders) {
		this.dataProviders = dataProviders;
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		if (param == null)
			throw new RuntimeException("$data指令参数不能为空, 必需为String"); // TODO 未国际化
		if (! (param instanceof String))
			throw new RuntimeException("$data指令参数必需为String"); // TODO 未国际化
		if (dataProviders == null)
			throw new RuntimeException("没有注册dataProviders"); // TODO 未国际化
		DataProvider dataProvider = (DataProvider)dataProviders.get((String)param);
		if (dataProvider == null)
			throw new RuntimeException("$data指令不支持:" + param
					+ "数据类型. 配置中只支持数据类型: " + dataProviders.keySet()
					+ ", 可以通过实现:"+DataProvider.class.getName()
					+ "接口扩展数据供给类型. 然后在commontemplate.properties中注册, 如: dataProvider{xxx}=com.xxx.XXXDataProvider()"); // TODO 未国际化
		BufferedOutputFilter bufferedFilter = new BufferedOutputFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String dataSource = bufferedFilter.getBuffered();
		Map data = dataProvider.getData(dataSource);
		context.getRootLocalContext().putAllVariables(data);
	}

	public boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
