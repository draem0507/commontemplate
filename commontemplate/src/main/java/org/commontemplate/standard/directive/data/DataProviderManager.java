package org.commontemplate.standard.directive.data;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DataProviderManager {

	private final static Map dataProviders = new TreeMap();

	static {
		dataProviders.put("json", new JsonDataProvider());
		dataProviders.put("properties", new PropertiesDataProvider());
		dataProviders.put("xml", new XmlDataProvider());
	}

	public static Set getDataProviderNames() {
		return dataProviders.keySet();
	}

	public static DataProvider getDataProvider(String name) throws Exception {
		return (DataProvider)dataProviders.get(name);
	}

}
