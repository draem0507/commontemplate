package org.commontemplate.tools.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFileField extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int FILES_AND_DIRECTORIES = JFileChooser.FILES_AND_DIRECTORIES;

	public static final int FILES_ONLY = JFileChooser.FILES_ONLY;

	public static final int DIRECTORIES_ONLY = JFileChooser.DIRECTORIES_ONLY;

	private final JTextField textField;

	private final JButton button;

	private int width = 300;

	private int height = 24;

	public JFileField() {
		this(null, FILES_AND_DIRECTORIES, null, true);
	}

	public JFileField(File defaultFile) {
		this(defaultFile, FILES_AND_DIRECTORIES, null, true);
	}

	public JFileField(int mode) {
		this(null, mode, null, true);
	}

	public JFileField(FileFilter fileFilter) {
		this(null, FILES_AND_DIRECTORIES, fileFilter, true);
	}

	public JFileField(File defaultFile, int mode) {
		this(defaultFile, mode, null, true);
	}

	public JFileField(File defaultFile, FileFilter fileFilter) {
		this(defaultFile, FILES_AND_DIRECTORIES, fileFilter, true);
	}

	public JFileField(boolean editable) {
		this(null, FILES_AND_DIRECTORIES, null, true);
	}

	public JFileField(File defaultFile, boolean editable) {
		this(defaultFile, FILES_AND_DIRECTORIES, null, editable);
	}

	public JFileField(File defaultFile, int mode, boolean editable) {
		this(defaultFile, mode, null, editable);
	}

	public JFileField(int mode, boolean editable) {
		this(null, mode, null, editable);
	}

	public JFileField(FileFilter fileFilter, boolean editable) {
		this(null, FILES_AND_DIRECTORIES, fileFilter, editable);
	}

	public JFileField(final File defaultFile, final int mode, final FileFilter fileFilter, final boolean editable) {
		this.setSize(width, height);
		this.setLayout(null);

		textField = new JTextField(defaultFile != null ? defaultFile.getAbsolutePath() : "");
		if (editable) {
			MenuBuilder.buildEditableTextMenu(textField);
		} else {
			textField.setEditable(false);
			textField.setBackground(Color.WHITE);
			MenuBuilder.buildReadonlyTextMenu(textField);
		}
		textField.setBounds(0, 0, 200, 24);
		this.add(textField);

		button = new JButton("浏览"); // TODO 未国际化
		button.setBounds(210, 0, 80, 24);
		this.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = null;
				File dir = null;
				String txt = textField.getText();
				if (txt != null && txt.length() > 0) {
					try {
						file = new File(txt);
						if (file.isDirectory())
							dir = file;
						else
							dir = file.getParentFile();
					} catch (Exception ex) {
						// ignore
					}
				}
				JFileChooser fc = new JFileChooser(dir != null && dir.exists() ? dir : new File(".")); //以上次打开的文件为默认路径打开文件选取择框图
				fc.setDialogTitle("请选择"); // TODO 未国际化
				fc.setApproveButtonText("选择");
				fc.setFileSelectionMode(mode);
				if (file != null && dir != file)
					fc.setSelectedFile(file);
				int ch = fc.showOpenDialog(null);
				if (ch == JFileChooser.APPROVE_OPTION)
					textField.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});
	}

	/**
	 * 获取文件名文本框
	 *
	 * @return 文件名文本框
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * 获取浏览按钮
	 *
	 * @return 浏览按钮
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * 设置宽度
	 *
	 * @param fieldWidth 文本框宽度
	 * @param buttonWidth 按钮宽度
	 */
	public void setWidth(int fieldWidth, int buttonWidth) {

	}

	/**
	 * 设置高度
	 *
	 * @param height 高度
	 */
	public void setHeight(int height) {

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		resetSize(width, height);
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		resetSize(width, height);
	}

	public void setBounds(Rectangle r) {
		setBounds(r.x, r.y, r.width, r.height);
	}

	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	private void resetSize(int width, int height) {
		if (this.width != width || this.height != height) {
			this.width = width;
			this.height = height;
		}
	}

}
