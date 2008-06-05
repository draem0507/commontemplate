package org.commontemplate.tools.viewer;

import java.io.File;
import java.util.Map;

public abstract class DataProviderSupport implements DataProvider {

	public Map getData(String dataPath) throws Exception {
		File file = new File(dataPath);
		if (! file.exists())
			return null;
		return getData(file);
	}

	protected abstract Map getData(File dataFile) throws Exception;
}
