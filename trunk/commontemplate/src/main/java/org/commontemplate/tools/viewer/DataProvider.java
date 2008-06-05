package org.commontemplate.tools.viewer;

import java.util.Map;

public interface DataProvider {

	Map getData(String dataPath) throws Exception;

}
