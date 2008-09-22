package org.commontemplate.tools.viewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.data.DataProvider;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.FileClassResourceLoader;
import org.commontemplate.tools.bean.PropertiesBeanFactory;
import org.commontemplate.tools.swing.CommonTemplateFrame;
import org.commontemplate.tools.swing.JFileField;

public class TemplateGenerator {

	public void generate(final File sourceFile) throws Exception {
		generate(sourceFile, false);
	}

	public void generate(final File sourceFile, final boolean debug) throws Exception {
		File targetFile = getSuffixFile(sourceFile, "html"); // 目标文件
		final CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplate模板生成器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440, 150);
		frame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);

		JLabel chooseLabel = new JLabel("生成后文件:");
		chooseLabel.setBounds(20, 20, 100, 24);
		frame.getContentPane().add(chooseLabel);

		final JFileField fileField = new JFileField(targetFile);
		fileField.setBounds(120, 20, 300, 24);
		frame.getContentPane().add(fileField);

		JButton generateButton = new JButton("生成"); // TODO 未国际化
		generateButton.setBounds(120, 60, 80, 24);
		frame.getContentPane().add(generateButton);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					TemplateGenerator.this.generate(sourceFile, new File(fileField.getTextField().getText()), debug);
				} catch (RuntimeException e1) {
					throw e1;
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});

		frame.setVisible(true);
		generateButton.requestFocus();
	}

	public static final String STANDARD_CONFIG_PATH = TemplateGenerator.class.getPackage().getName().replace('.', '/') + "/commontemplate.properties";

	public File generateDefault(File sourceFile) throws Exception {
		return generateDefault(sourceFile, false);
	}

	public File generateDefault(File sourceFile, boolean debug) throws Exception {
		File targetFile = getSuffixFile(sourceFile, "html"); // 目标文件
		generate(sourceFile, targetFile, debug);
		return targetFile;
	}

	private void generate(File sourceFile, File targetFile, boolean debug) throws Exception {
		BeanFactory beanFactory;
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
		Map dataProviders = (Map)beanFactory.getBean("dataProviders");
		Map data = getData(sourceFile, dataProviders); // 查找模板数据
		ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration(beanFactory);
		Engine engine = new Engine(config);
		Writer writer = null;
		try {
			writer = new FileWriter(targetFile);
			Context context = null;
			try {
				context = engine.createContext(writer);
				if (debug)
					context.setDebug(true);
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

	private Map getData(File sourceFile, Map dataProviders) throws Exception {
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
