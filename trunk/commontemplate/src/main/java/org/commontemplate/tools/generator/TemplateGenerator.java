package org.commontemplate.tools.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.data.DataProvider;
import org.commontemplate.standard.debug.Breakpoint;
import org.commontemplate.standard.debug.DebugManager;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.FileClassResourceLoader;
import org.commontemplate.tools.bean.PropertiesBeanFactory;
import org.commontemplate.util.UrlUtils;

public class TemplateGenerator {

	public static final String STANDARD_CONFIG_PATH = TemplateGenerator.class.getPackage().getName().replace('.', '/') + "/commontemplate.properties";

	private final BeanFactory beanFactory;

	private final Map dataProviders;

	private final Engine engine;

	public TemplateGenerator(File sourceFile) {
		try {
			// 首先查找模板所在目录的commontemplate.properties
			File configFile = getFile(sourceFile, "commontemplate.properties");
			if (configFile == null || ! configFile.exists()) {
				// 其次查找安装目录的commontemplate.properties
				String exedir = System.getProperty("launch4j.exedir");
				if (exedir != null) {
					configFile = new File(exedir + "/commontemplate.properties");
				}
			}
			if (configFile != null && configFile.exists()) {
				beanFactory = new PropertiesBeanFactory(configFile.getCanonicalPath(), STANDARD_CONFIG_PATH, new FileClassResourceLoader());
			} else {
				// 否则使用默认的commontemplate.properties
				beanFactory = new PropertiesBeanFactory(STANDARD_CONFIG_PATH);
			}
			dataProviders = (Map)beanFactory.getBean("dataProviders");
			ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration(beanFactory);
			engine = new Engine(config);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public File getDefaultTargetFile(File sourceFile) throws Exception {
		return getSuffixFile(sourceFile, "html");
	}

	public void generateDirectory(File sourceDirectory, File targetDirectory,
			String sourceSuffix, String targetSuffix, String sourceEncoding, String targetEncoding,
			boolean includeEmptyDirectory) throws Exception {
		generateDirectory(sourceDirectory, targetDirectory, new TemplateFileFilter(sourceSuffix), targetSuffix, sourceEncoding, targetEncoding, includeEmptyDirectory);
	}

	private void generateDirectory(File sourceDirectory, File targetDirectory,
			TemplateFileFilter templateFileFilter, String targetSuffix,
			String sourceEncoding, String targetEncoding,
			boolean includeEmptyDirectory) throws Exception {
		if (sourceDirectory == null || targetDirectory == null)
			return;
		if (includeEmptyDirectory) {
			if (! targetDirectory.exists())
				targetDirectory.mkdirs();
		}
		File[] files = sourceDirectory.listFiles(templateFileFilter);
		if (files != null && files.length > 0) {
			for (int i = 0, n = files.length; i < n; i ++) {
				File file = files[i];
				if (file.isDirectory()) {
					generateDirectory(file, new File(targetDirectory, file.getName()), templateFileFilter, targetSuffix, sourceEncoding, targetEncoding, includeEmptyDirectory);
				} else {
					generateFile(file, getSuffixFile(new File(targetDirectory, file.getName()), targetSuffix), sourceEncoding, targetEncoding, false, false);
				}
			}
		}
	}

	public void generateFile(File sourceFile, File targetFile,
			String sourceEncoding, String targetEncoding,
			boolean debug, boolean view) throws Exception {
		if (sourceFile == null || targetFile == null)
			return;
		if (debug) {
			String sourcePath = sourceFile.getCanonicalPath();
			sourcePath = UrlUtils.cleanUrl(sourcePath);
			DebugManager.getInstance().addBreakpoint(new Breakpoint(sourcePath, 0));
		}
		Map data = getData(sourceFile); // 查找模板数据
		File targetDir = targetFile.getParentFile();
		if (! targetDir.exists())
			targetDir.mkdirs();
		Writer writer = null;
		try {
			if (targetEncoding != null)
				writer = new OutputStreamWriter(new FileOutputStream(targetFile), targetEncoding);
			else
				writer = new OutputStreamWriter(new FileOutputStream(targetFile));
			Template template;
			if (sourceEncoding != null && sourceEncoding.length() > 0)
				template = engine.getTemplate(sourceFile.getCanonicalPath(), sourceEncoding);
			else
				template = engine.getTemplate(sourceFile.getCanonicalPath());
			Context context = null;
			try {
				context = engine.createContext(writer);
				if (debug)
					context.setDebug(true);
				context.pushLocalContext(data);
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
		if (view) {
			Runtime.getRuntime().exec("cmd /c start " + UrlUtils.cleanWindowsPath(targetFile.getCanonicalPath()));
		}
	}

	private File getSuffixFile(File sourceFile, String suffix) throws Exception {
		String prefix;
		String name = sourceFile.getName();
		int i = name.lastIndexOf('.');
		if (i > -1) {
			prefix = name.substring(0, i + 1);
		} else {
			prefix = name + ".";
		}
		return getFile(sourceFile, prefix + suffix);
	}

	private File getFile(File sourceFile, String fileName) throws Exception {
		return new File(sourceFile.getParentFile(), fileName).getCanonicalFile();
	}

	private Map getData(File sourceFile) throws Exception {
		if (dataProviders != null) {
			for (Iterator iterator = dataProviders.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String suffix = (String)entry.getKey();
				DataProvider dataProvider = (DataProvider)entry.getValue();
				if (suffix != null && dataProvider != null) {
					File file = getSuffixFile(sourceFile, suffix);
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
