package org.commontemplate.tools.viewer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TemplateViewer {

	private TemplateGenerator generator = new TemplateGenerator();

	public void view(String sourcePath) {
		try {
			// 前置条件
			if (sourcePath == null || sourcePath.length() == 0)
				return ;
			if (! new File(sourcePath).exists())
				return ;

			// 去除文件后缀
			String sourceName;
			int i = sourcePath.lastIndexOf('.');
			if (i > -1) {
				sourceName = sourcePath.substring(0, i);
			} else {
				sourceName = sourcePath;
			}

			// 读取数据
			Map data = readJson(sourceName + ".json");
			if (data == null)
				readXml(sourceName + ".xml");
			if (data == null)
				readProperties(sourceName + ".properties");
			if (data == null)
				data = new HashMap();

			// 生成并打开html文件
			String targetPath = sourceName + ".html";
			generator.generate(data, sourcePath, targetPath);
			Runtime.getRuntime().exec("cmd /c start "+ targetPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读取.json文件, 组装成Map数据
	private Map readJson(String dataPath) {
		if (! new File(dataPath).exists())
			return null;
		return new HashMap(); // TODO 未读取
	}

	// 读取.xml文件, 组装成Map数据
	private Map readXml(String dataPath) {
		if (! new File(dataPath).exists())
			return null;
		return new HashMap(); // TODO 未读取
	}

	// 读取.properties文件, 组装成Map数据
	private Map readProperties(String dataPath) {
		if (! new File(dataPath).exists())
			return null;
		return new HashMap(); // TODO 未读取
	}

}
