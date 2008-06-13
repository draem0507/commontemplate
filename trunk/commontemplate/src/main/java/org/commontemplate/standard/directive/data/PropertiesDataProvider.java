package org.commontemplate.standard.directive.data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.commontemplate.util.StringUtils;
import org.commontemplate.util.TypeUtils;

/**
 * 属性格式数据供给策略. 如:<br/>
 * mail.from=xxx@xxx.com<br/>
 * mail.to=yyy@yyy.com<br/>
 * users.0.id=1<br/>
 * users.0.name=james<br/>
 * users.1.id=2<br/>
 * users.1.name=kent<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class PropertiesDataProvider extends InputStreamDataProvider {

	public Map getData(InputStream dataInputStream) throws Exception {
		Properties properties = new Properties();
		properties.load(dataInputStream);
		Map map = new HashMap();
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			Object value = TypeUtils.parseValue((String)entry.getValue());
			String[] tokens = StringUtils.split(key, '.');
			if (tokens == null || tokens.length == 0)
				throw new RuntimeException("变量名不能为空!");
			Map parent = map;
			for (int i = 0, n = tokens.length; i < n; i ++) {
				String token = tokens[i];
				if (token == null || token.length() == 0)
					throw new RuntimeException("变量名中不能有连续的\".\"点号!");
				if (i == n - 1) { // 最后一节
					parent.put(TypeUtils.parseValue(token), value);
				} else {
					Object obj = parent.get(token);
					if (obj instanceof Map) {
						parent = (Map)obj;
					} else {
						Map node = new HashMap();
						parent.put(TypeUtils.parseValue(token), node);
						parent = node;
					}
				}
			}
		}
		return map;
	}

}
