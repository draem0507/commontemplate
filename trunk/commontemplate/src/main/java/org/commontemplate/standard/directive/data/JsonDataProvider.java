package org.commontemplate.standard.directive.data;

import java.io.File;
import java.util.Map;

import org.commontemplate.util.IOUtils;
import org.commontemplate.util.json.JSONObject;
import org.commontemplate.util.json.JSONUtils;

public class JsonDataProvider implements DataProvider {

	public Map getData(File dataFile) throws Exception {
		return getData(IOUtils.readToString(dataFile));
	}

	public Map getData(String dataSource) throws Exception {
		JSONObject jsonObject = new JSONObject(dataSource);
		return JSONUtils.jsonToMap(jsonObject);
	}

}
