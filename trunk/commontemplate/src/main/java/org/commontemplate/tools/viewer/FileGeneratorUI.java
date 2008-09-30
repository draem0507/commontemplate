package org.commontemplate.tools.viewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.commontemplate.tools.generator.TemplateGenerator;
import org.commontemplate.tools.swing.CommonTemplateFrame;
import org.commontemplate.tools.swing.JFileField;

public class FileGeneratorUI {

	private final TemplateGenerator templateGenerator;

	public FileGeneratorUI(TemplateGenerator templateGenerator) {
		this.templateGenerator = templateGenerator;
	}

	public void generate(final File sourceFile) throws Exception {
		File targetFile = templateGenerator.getDefaultTargetFile(sourceFile);
		final CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplate模板生成器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440, 200);
		frame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);

		JLabel chooseLabel = new JLabel("目标文件:");
		chooseLabel.setBounds(20, 20, 100, 24);
		frame.getContentPane().add(chooseLabel);

		final JFileField fileField = new JFileField(targetFile, JFileField.FILES_ONLY, true);
		fileField.setBounds(120, 20, 300, 24);
		frame.getContentPane().add(fileField);

		final JCheckBox debugCheckBox = new JCheckBox("是否调试", false);
		debugCheckBox.setBounds(20, 60, 100, 24);
		frame.getContentPane().add(debugCheckBox);

		final JCheckBox viewCheckBox = new JCheckBox("是否用浏览器打开", false);
		viewCheckBox.setBounds(120, 60, 180, 24);
		frame.getContentPane().add(viewCheckBox);

		JButton generateButton = new JButton("生成"); // TODO 未国际化
		generateButton.setBounds(120, 100, 80, 24);
		frame.getContentPane().add(generateButton);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Thread() {
					public void run() {
						try {
							if (fileField.getTextField().getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(frame, "目标文件名不能为空!", "提示", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							templateGenerator.generateFile(sourceFile, new File(fileField.getTextField().getText()), debugCheckBox.isSelected(), viewCheckBox.isSelected());
						} catch (RuntimeException e1) {
							throw e1;
						} catch (Exception e2) {
							throw new RuntimeException(e2);
						}
					}
				}.start();
			}
		});

		frame.setVisible(true);
		generateButton.requestFocus();
	}

}
