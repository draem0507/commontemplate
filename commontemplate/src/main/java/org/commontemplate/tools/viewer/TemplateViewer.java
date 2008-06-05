package org.commontemplate.tools.viewer;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.commontemplate.util.UrlCleaner;

public class TemplateViewer {

	private final TemplateGenerator generator = new TemplateGenerator();

	private final Map dataProviders = new TreeMap();

	public TemplateViewer() {
		super();
		dataProviders.put("json", new JsonDataProvider());
		dataProviders.put("properties", new PropertiesDataProvider());
		dataProviders.put("xml", new XmlDataProvider());
	}

	public void view(String sourcePath) {
		try {
			// 前置条件
			if (sourcePath == null || sourcePath.length() == 0)
				return ;
			if (! new File(sourcePath).exists())
				return ;

			// 去除文件后缀
			String sourcePrefix;
			int i = sourcePath.lastIndexOf('.');
			if (i > -1) {
				sourcePrefix = sourcePath.substring(0, i + 1);
			} else {
				sourcePrefix = sourcePath + ".";
			}

			// 生成并打开html文件
			String targetPath = sourcePrefix + "html";

			Map data = getData(sourcePrefix);

			generator.generate(data, sourcePath, targetPath);
			Runtime.getRuntime().exec("cmd /c start " + UrlCleaner.cleanWindowsPath(targetPath));
		} catch (Exception e) {
			TemplateViewerUI.showException(e);
		}
	}

	private Map getData(String sourcePrefix) throws Exception {
		for (Iterator iterator = dataProviders.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String suffix = (String)entry.getKey();
			DataProvider dataProvider = (DataProvider)entry.getValue();
			Map data = dataProvider.getData(sourcePrefix + suffix);
			if (data != null)
				return data;
		}
		return new HashMap();
	}

}
