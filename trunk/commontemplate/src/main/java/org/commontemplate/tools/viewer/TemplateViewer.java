package org.commontemplate.tools.viewer;

import java.io.File;
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
			File sourceFile = new File(sourcePath).getCanonicalFile(); // 源文件
			if (! sourceFile.exists())
				return ;
			Map data = getData(sourceFile); // 查找模板数据
			File targetFile = getFile(sourceFile, "html"); // 目标文件
			generator.generate(data, sourceFile, targetFile);
			Runtime.getRuntime().exec("cmd /c start " + UrlCleaner.cleanWindowsPath(targetFile.getCanonicalPath()));
		} catch (Exception e) {
			TemplateViewerUI.showException(e);
		}
	}

	private File getFile(File sourceFile, String suffix) throws Exception {
		String prefix;
		String name = sourceFile.getName();
		int i = name.lastIndexOf('.');
		if (i > -1) {
			prefix = name.substring(0, i + 1);
		} else {
			prefix = name + ".";
		}
		return new File(sourceFile.getParentFile(), prefix + suffix).getCanonicalFile();
	}

	private Map getData(File sourceFile) throws Exception {
		for (Iterator iterator = DataProviderManager.getDataProviderNames().iterator(); iterator.hasNext();) {
			String suffix = (String)iterator.next();
			DataProvider dataProvider = (DataProvider)DataProviderManager.getDataProvider(suffix);
			File file = getFile(sourceFile, suffix);
			if (file.exists()) {
				Map data = dataProvider.getData(file);
				if (data != null)
					return data;
			}
		}
		return new HashMap();
	}

}
