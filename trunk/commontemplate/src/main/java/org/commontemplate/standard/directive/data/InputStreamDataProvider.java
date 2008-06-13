package org.commontemplate.standard.directive.data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public abstract class InputStreamDataProvider implements DataProvider {

	public Map getData(String dataSource) throws Exception {
		return getData(new ByteArrayInputStream(dataSource.getBytes()));
	}

	public Map getData(File dataFile) throws Exception {
		return getData(new FileInputStream(dataFile));
	}

}
