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
import javax.swing.JTextField;

import org.commontemplate.tools.generator.TemplateGenerator;
import org.commontemplate.tools.swing.CommonTemplateFrame;
import org.commontemplate.tools.swing.JFileField;

public class DirectoryGeneratorUI {

	private final TemplateGenerator templateGenerator;

	public DirectoryGeneratorUI(TemplateGenerator templateGenerator) {
		this.templateGenerator = templateGenerator;
	}

	public void generate(final File sourceDir) throws Exception {
		final CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplate模板生成器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440, 220);
		frame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);

		JLabel chooseLabel = new JLabel("目标文件夹:");
		chooseLabel.setBounds(20, 20, 100, 24);
		frame.getContentPane().add(chooseLabel);

		final JFileField dirField = new JFileField(new File(sourceDir.getAbsolutePath() + "_result"), JFileField.DIRECTORIES_ONLY, true);
		dirField.setBounds(120, 20, 300, 24);
		frame.getContentPane().add(dirField);

		final JLabel sourceSuffixLabel = new JLabel("源文件后缀:");
		sourceSuffixLabel.setBounds(20, 50, 100, 24);
		frame.getContentPane().add(sourceSuffixLabel);
		final JTextField sourceSuffixField = new JTextField("ctl");
		sourceSuffixField.setBounds(120, 50, 80, 24);
		frame.getContentPane().add(sourceSuffixField);

		final JLabel targetSuffixLabel = new JLabel("目标文件后缀:");
		targetSuffixLabel.setBounds(220, 50, 100, 24);
		frame.getContentPane().add(targetSuffixLabel);
		final JTextField targetSuffixField = new JTextField("html");
		targetSuffixField.setBounds(320, 50, 80, 24);
		frame.getContentPane().add(targetSuffixField);

		final JLabel sourceEncodingLabel = new JLabel("源文件编码:");
		sourceEncodingLabel.setBounds(20, 80, 100, 24);
		frame.getContentPane().add(sourceEncodingLabel);
		final JTextField sourceEncodingField = new JTextField("UTF-8");
		sourceEncodingField.setBounds(120, 80, 80, 24);
		frame.getContentPane().add(sourceEncodingField);

		final JLabel targetEncodingLabel = new JLabel("目标文件编码:");
		targetEncodingLabel.setBounds(220, 80, 100, 24);
		frame.getContentPane().add(targetEncodingLabel);
		final JTextField targetEncodingField = new JTextField("UTF-8");
		targetEncodingField.setBounds(320, 80, 80, 24);
		frame.getContentPane().add(targetEncodingField);

		final JCheckBox emptyCheckBox = new JCheckBox("是否包含空目录", false);
		emptyCheckBox.setBounds(20, 110, 180, 24);
		frame.getContentPane().add(emptyCheckBox);

		JButton generateButton = new JButton("生成"); // TODO 未国际化
		generateButton.setBounds(180, 150, 80, 24);
		frame.getContentPane().add(generateButton);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Thread() {
					public void run() {
						try {
							if (dirField.getTextField().getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(frame, "目标文件夹不能为空!", "提示", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							templateGenerator.generateDirectory(sourceDir, new File(dirField.getTextField().getText().trim()),
									sourceSuffixField.getText().trim(), targetSuffixField.getText().trim(),
									sourceEncodingField.getText().trim(), targetEncodingField.getText().trim(),
									emptyCheckBox.isSelected());
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
