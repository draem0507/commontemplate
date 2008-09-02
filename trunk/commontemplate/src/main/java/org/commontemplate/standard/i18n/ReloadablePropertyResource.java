package org.commontemplate.standard.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReloadablePropertyResource implements ReloadableResource {
	
	private Map map;
	
	public Object handleGetObject(String key) {
		
		return map.get(key);
	}

	public void loadFromURL(URL url, String encoding) throws IOException{
		// 如果 encoding 为空，那么就使用默认的编码。
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File(url.getFile())));
		map = new HashMap(properties);
	}

}
