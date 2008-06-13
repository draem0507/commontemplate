package org.commontemplate.standard.directive.data;

import java.io.File;
import java.util.Map;

public interface DataProvider {

	Map getData(String dataSource) throws Exception;

	Map getData(File dataFile) throws Exception;

}
