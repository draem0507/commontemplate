package org.commontemplate.standard.directive.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
			Object parent = map;
			for (int i = 0, n = tokens.length; i < n; i ++) {
				String token = tokens[i];
				if (token == null || token.length() == 0)
					throw new RuntimeException("变量名中不能有连续的\".\"点号!");
				if (TypeUtils.isInteger(token)) {
					if (! (parent instanceof List))
						throw new RuntimeException("数组下标与对象属性不能同级! 请检查: " + key);
					List parentList = (List)parent;
					int index = TypeUtils.parseInteger(token).intValue();
					if (i == n - 1) { // 最后一节
						if (index < parentList.size()) {
							parentList.set(index, value);
						} else {
							for (int j = parentList.size(); j < index; j ++)
								parentList.add(null);
							parentList.add(value);
						}
					} else {
						Object obj = index >= parentList.size() ? null : parentList.get(index);
						if (obj != null) {
							parent = obj;
						} else {
							String next = tokens[i + 1];
							Object node;
							if (TypeUtils.isInteger(next)) {
								node = new ArrayList();
							} else {
								node = new HashMap();
							}
							if (index < parentList.size()) {
								parentList.set(index, node);
							} else {
								for (int j = parentList.size(); j < index; j ++)
									parentList.add(null);
								parentList.add(node);
							}
							parent = node;
						}
					}
				} else {
					if (! TypeUtils.isNamed(token))
						throw new RuntimeException("对象属性名不能为: " + token);
					if (! (parent instanceof Map))
						throw new RuntimeException("对象属性与数组下标不能同级! 请检查: " + key);
					Map parentMap = (Map)parent;
					if (i == n - 1) { // 最后一节
						parentMap.put(token, value);
					} else {
						Object obj = parentMap.get(token);
						if (obj != null) {
							parent = obj;
						} else {
							String next = tokens[i + 1];
							Object node;
							if (TypeUtils.isInteger(next)) {
								node = new ArrayList();
							} else {
								node = new HashMap();
							}
							parentMap.put(token, node);
							parent = node;
						}
					}
				}
			}
		}
		return map;
	}

}
