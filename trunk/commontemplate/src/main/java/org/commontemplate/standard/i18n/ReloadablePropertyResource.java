package org.commontemplate.standard.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.util.EncodingProperties;

import com.opensymphony.oscache.util.StringUtil;

public class ReloadablePropertyResource implements ReloadableResource {
	
	private Map map;
	
	public Object handleGetObject(String key) {
		
		return map.get(key);
	}

	public void loadFromURL(URL url, String encoding) throws IOException{
		
		EncodingProperties exProperties = new EncodingProperties();
		if(StringUtil.isEmpty(encoding)) {
			// 如果 encoding 为空，那么就使用默认的编码。
			exProperties.load(new FileInputStream(new File(url.getFile())));
		} else {
			exProperties.load(new FileInputStream(new File(url.getFile())), encoding);
		}
		if(map == null) {
			map = new HashMap(exProperties);
		}else{
			map.putAll(new HashMap(exProperties));
		}
	}

}
