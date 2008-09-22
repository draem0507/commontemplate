 package org.commontemplate.tools.converter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.commontemplate.tools.swing.CommonTemplateFrame;
import org.commontemplate.tools.swing.JFileField;

/**
 * 模板转换exe程序入口
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateConverterExecute {

	public void convert() throws Exception {
		final CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplate模板转换器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 200);
		frame.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);

		JLabel typeLabel = new JLabel("模板源类型:");
		typeLabel.setBounds(20, 20, 100, 24);
		frame.getContentPane().add(typeLabel);

		JComboBox typeComboBox = new JComboBox(new String[]{"Velocity", "FreeMarker"});
		typeComboBox.setBounds(130, 20, 100, 24);
		frame.getContentPane().add(typeComboBox);

		JLabel sourceLabel = new JLabel("模板源文件/目录:");
		sourceLabel.setBounds(20, 50, 110, 24);
		frame.getContentPane().add(sourceLabel);

		final JFileField sourceField = new JFileField();
		sourceField.setBounds(130, 50, 400, 24);
		frame.getContentPane().add(sourceField);

		JLabel targetLabel = new JLabel("转换后文件/目录:");
		targetLabel.setBounds(20, 80, 110, 24);
		frame.getContentPane().add(targetLabel);

		final JFileField targetField = new JFileField();
		targetField.setBounds(130, 80, 400, 24);
		frame.getContentPane().add(targetField);

		JButton convertButton = new JButton("转换"); // TODO 未国际化
		convertButton.setBounds(130, 110, 80, 24);
		frame.getContentPane().add(convertButton);
		convertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					TemplateConverterExecute.this.convert(
							sourceField.getTextField().getText(),
							targetField.getTextField().getText());
				} catch (RuntimeException e1) {
					throw e1;
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});

		frame.setVisible(true);
		convertButton.requestFocus();
	}

	public void convert(String sourceDir, String targetDir) throws Exception {
		// TODO 实现模板转换
	}

	public static void main(String[] args) {
		try {
			new TemplateConverterExecute().convert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
