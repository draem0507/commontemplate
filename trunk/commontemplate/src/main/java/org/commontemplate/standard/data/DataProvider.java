package org.commontemplate.standard.data;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public interface DataProvider {

	Map getData(String dataSource) throws Exception;

	Map getData(File dataFile) throws Exception;

	Map getData(InputStream dataInputStream) throws Exception;
}
