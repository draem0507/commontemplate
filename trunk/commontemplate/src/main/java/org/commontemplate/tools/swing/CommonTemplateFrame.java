package org.commontemplate.tools.swing;

import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class CommonTemplateFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	static {
		try {
			// 启用图形UI
			// System.setProperty("java.awt.headless", "ture");
			// 设置swing样式为当前系统风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CommonTemplateFrame() {
		super();
		init();
	}

	public CommonTemplateFrame(GraphicsConfiguration gc) {
		super(gc);
		init();
	}

	public CommonTemplateFrame(String title, GraphicsConfiguration gc) {
		super(filterTitle(title), gc);
		init();
	}

	public CommonTemplateFrame(String title) {
		super(filterTitle(title));
		init();
	}

	private void init() {
		super.setIconImage(ImageFactory.getImage("org/commontemplate/tools/swing/logo.gif"));
	}

	public void setTitle(String title) {
		super.setTitle(filterTitle(title));
	}

	private static String filterTitle(String title) {
		return title + " (http://www.commontemplate.org)";
	}

}
