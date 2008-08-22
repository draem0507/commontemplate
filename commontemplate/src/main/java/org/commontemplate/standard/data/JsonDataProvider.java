package org.commontemplate.standard.data;

import java.util.Map;

import org.commontemplate.util.json.JSONObject;
import org.commontemplate.util.json.JSONUtils;

/**
 * JSON格式数据供给策略. 如:<br/>
 * {mail: {from: "xxx@xxx.com", to: "yyy@yyy.com"}, users: [{id: 1, name: "james"}, {id: 2, name: "kent"}]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class JsonDataProvider extends StringDataProvider {

	public Map getData(String dataSource) throws Exception {
		JSONObject jsonObject = new JSONObject(dataSource);
		return JSONUtils.jsonToMap(jsonObject);
	}

}
