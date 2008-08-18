package org.commontemplate.tools.viewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.directive.data.DataProvider;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.PropertiesBeanFactory;
import org.commontemplate.tools.swing.CommonTemplateFrame;

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

	public void generate(final File sourceFile) throws Exception {
		File targetFile = getFile(sourceFile, "html"); // 目标文件
		final File targetDir = targetFile.getParentFile();
		final CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplateViewer - Choose");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(540, 160);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);

		JLabel chooseLabel = new JLabel("请选择模板生成目标位置");
		chooseLabel.setBounds(20, 20, 300, 24);
		final JTextField fileField = new JTextField(targetFile.getCanonicalPath());
		fileField.setBounds(20, 60, 300, 24);
		JButton browseButton = new JButton("浏览"); // TODO 未国际化
		browseButton.setBounds(340, 60, 80, 24);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = null;
				File dir = null;
				String txt = fileField.getText();
				if (txt != null && txt.length() > 0) {
					try {
						file = new File(txt);
						dir = file.getParentFile();
					} catch (Exception ex) {
						// ignore
					}
				}
				JFileChooser fc = new JFileChooser(dir != null && dir.exists() ? dir : targetDir); //以上次打开的文件为默认路径打开文件选取择框图
				fc.setDialogTitle("请选择模板生成目标位置"); // TODO 未国际化
				fc.setApproveButtonText("选择");
				if (file != null)
					fc.setSelectedFile(file);
				int ch = fc.showOpenDialog(null);
				if (ch == JFileChooser.APPROVE_OPTION)
					fileField.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});
		JButton chooseButton = new JButton("生成"); // TODO 未国际化
		chooseButton.setBounds(440, 60, 80, 24);
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					TemplateGenerator.this.generate(sourceFile, new File(fileField.getText()));
				} catch (RuntimeException e1) {
					throw e1;
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		frame.getContentPane().add(chooseLabel);
		frame.getContentPane().add(fileField);
		frame.getContentPane().add(browseButton);
		frame.getContentPane().add(chooseButton);
		frame.setVisible(true);
	}

	public void generate(File sourceFile, File targetFile) throws Exception {
		Map data = getData(sourceFile); // 查找模板数据
		generate(data, sourceFile, targetFile);
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

	public File getFile(File sourceFile, String suffix) throws Exception {
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

	public Map getData(File sourceFile) throws Exception {
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
