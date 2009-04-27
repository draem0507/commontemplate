package org.commontemplate.standard.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.util.EncodingProperties;
import org.commontemplate.util.log.Logger;
import org.commontemplate.util.log.LoggerFactory;

import com.opensymphony.oscache.util.StringUtil;

public class ReloadablePropertyResource implements ReloadableResource {
	
	private static Logger logger = LoggerFactory.getLogger(ReloadablePropertyResource.class);
	private Map map;
	
	
	public Object handleGetObject(String key) {
		
		return map.get(key);
	}

	public void loadFromURL(URL url, String encoding) throws IOException{
		
		EncodingProperties exProperties = new EncodingProperties();
		// 如果文件路径中包含空格，非英文字符，需要 decode一下。
		String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
		if(logger.isDebugEnabled()) {
			logger.debug("loadFromURL.decode filepath = " + filePath);
		}
		if(StringUtil.isEmpty(encoding)) {
			// 如果 encoding 为空，那么就使用默认的编码。			
			exProperties.load(new FileInputStream(new File(filePath)));
		} else {
			exProperties.load(new FileInputStream(new File(filePath)), encoding);
		}
		if(map == null) {
			map = new HashMap(exProperties);
		}else{
			map.putAll(new HashMap(exProperties));
		}
	}

}
