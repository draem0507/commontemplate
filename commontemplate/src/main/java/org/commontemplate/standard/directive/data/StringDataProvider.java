package org.commontemplate.standard.directive.data;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.commontemplate.util.IOUtils;

public abstract class StringDataProvider implements DataProvider {

	public Map getData(File dataFile) throws Exception {
		return getData(IOUtils.readToString(dataFile));
	}

	public Map getData(InputStream dataInputStream) throws Exception {
		return getData(IOUtils.readToString(dataInputStream));
	}
}