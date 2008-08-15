package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.directive.data.DataProvider;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.PropertiesBeanFactory;

public class TemplateGenerator {

	private final BeanFactory beanFactory;

	private final Map dataProviders;

	private final ConfigurationSettings config;

	private final Engine engine;

	public TemplateGenerator() {
		this.beanFactory = new PropertiesBeanFactory(TemplateGenerator.class.getPackage().getName().replace('.', '/') + "/commontemplate.properties");
		this.dataProviders = (Map)beanFactory.getBean("dataProviders");
		this.config = PropertiesConfigurationLoader.loadConfiguration(beanFactory);
		this.engine = new Engine(config);
	}

	public String generate(File sourceFile) throws Exception {
		Map data = getData(sourceFile); // 查找模板数据
		File targetFile = getFile(sourceFile, "html"); // 目标文件
		generate(data, sourceFile, targetFile);
		return targetFile.getCanonicalPath();
	}

	public void generate(Map data, File sourceFile, File targetFile) throws IOException {
		Writer writer = null;
		try {
			writer = new FileWriter(targetFile);
			Context context = null;
			try {
				context = engine.createContext(writer);
				context.pushLocalContext(data);
				Template template = engine.getTemplate(sourceFile.getCanonicalPath());
				template.render(context);
				writer.flush();
				context.clear();
			} finally {
				if (context != null)
					context.clear();
			}
		} finally {
			if (writer != null)
				writer.close();
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
		if (dataProviders != null) {
			for (Iterator iterator = dataProviders.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String suffix = (String)entry.getKey();
				DataProvider dataProvider = (DataProvider)entry.getValue();
				if (suffix != null && dataProvider != null) {
					File file = getFile(sourceFile, suffix);
					if (file.exists()) {
						Map data = dataProvider.getData(file);
						if (data != null)
							return data;
					}
				}
			}
		}
		return new HashMap();
	}

}
