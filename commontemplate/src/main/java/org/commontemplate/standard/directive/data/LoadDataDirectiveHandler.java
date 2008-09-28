package org.commontemplate.standard.directive.data;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.core.Resource;
import org.commontemplate.standard.data.DataProvider;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.IOUtils;

/**
 * 数据加载指令. 使用如:
 * $load{xml : "xxx.xml"} 或 $load{"xxx.xml"}
 *
 * @author liangfei0201@163.com
 *
 */
public class LoadDataDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Map dataProviders;

	public void setDataProviders(Map dataProviders) {
		this.dataProviders = dataProviders;
	}

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			String path = (String)param;
			loadData(context, getSuffix(path), path, null);
		} else if (param instanceof Entry) {
			Entry entry = (Entry)param;
			loadData(context, String.valueOf(entry.getKey()), String.valueOf(entry.getValue()), null);
		} else if (param instanceof List) {
			List list = (List)param;
			Assert.assertTrue(list.size() == 2, "$load指令参数错误!");
			Assert.assertTrue(list.get(1) instanceof String, "$load指令参数错误!");
			String encoding = (String)list.get(1);
			if (list.get(0) instanceof String) {
				String path = (String)list.get(0);
				loadData(context, getSuffix(path), path, encoding);
			} else if (list.get(0) instanceof Entry) {
				Entry entry = (Entry)list.get(0);
				loadData(context, String.valueOf(entry.getKey()), String.valueOf(entry.getValue()), encoding);
			} else {
				Assert.fail("$load指令参数错误!"); // TODO 未国际化
			}
		} else {
			Assert.fail("$load指令参数错误!"); // TODO 未国际化
		}
	}

	private String getSuffix(String path) {
		int i = path.lastIndexOf('.');
		Assert.assertTrue(i >= 0 && i < path.length() - 1, "必需有扩展名!"); // TODO 未国际化
		return path.substring(i + 1);
	}

	private void loadData(Context context, String type, String path, String encoding) throws Exception {
		if (dataProviders == null)
			throw new RuntimeException("没有注册dataProviders");
		DataProvider dataProvider = (DataProvider)dataProviders.get((String)type);
		if (dataProvider == null)
			throw new RuntimeException("$data指令不支持:" + type
					+ "数据类型. 配置中只支持数据类型: " + dataProviders.keySet()
					+ ", 可以通过实现:"+DataProvider.class.getName()
					+ "接口扩展数据供给类型. 然后在commontemplate.properties中注册, 如: dataProvider{xxx}=com.xxx.XXXDataProvider()"); // TODO 未国际化
		Resource resource = context.getTemplateLoader().getResource(path, encoding);
		Map data = dataProvider.getData(IOUtils.readToString(resource.getReader()));
		context.getRootLocalContext().putAllVariables(data);
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
