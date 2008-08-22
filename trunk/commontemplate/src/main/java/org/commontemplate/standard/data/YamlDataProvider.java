package org.commontemplate.standard.data;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.commontemplate.util.yaml.Yaml;

public class YamlDataProvider implements DataProvider {

	public Map getData(InputStream dataInputStream) throws Exception {
		return (Map)Yaml.load(dataInputStream);
	}

	public Map getData(String dataSource) throws Exception {
		return (Map)Yaml.load(dataSource);
	}

	public Map getData(File dataFile) throws Exception {
		return (Map)Yaml.load(dataFile);
	}

}
