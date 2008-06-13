package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.standard.directive.data.DataProvider;
import org.commontemplate.standard.directive.data.DataProviderManager;
import org.commontemplate.util.UrlCleaner;

public class TemplateViewer {

	private final TemplateGenerator generator = new TemplateGenerator();

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
		for (Iterator iterator = DataProviderManager.getDataProviderNames().iterator(); iterator.hasNext();) {
			String suffix = (String)iterator.next();
			DataProvider dataProvider = (DataProvider)DataProviderManager.getDataProvider(suffix);
			String dataPath = sourcePrefix + suffix;
			File file = new File(dataPath);
			if (file.exists()) {
				Map data = dataProvider.getData(file);
				if (data != null)
					return data;
			}
		}
		return new HashMap();
	}

}
