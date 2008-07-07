package org.commontemplate.tools.exe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.commontemplate.tools.swing.CommonTemplateFrame;

public class MainUI {

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

	public static void showSettings() {
		CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplateViewer - Help");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(new BorderLayout());

		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setBackground(Color.WHITE);
		area.setText("一、运行方式：\n双击*.ctl文件, 或右键菜单选择\"CommonTemplate\"打开, 即可运行．\n\n二、模板数据查找方式: \n查找与模板同名但不同后缀的数据文件, 如: \n打开test.ctl时, 查找test.xml, test.json, test.properties \n\n三、模板数据格式\n(1)XML数据格式: \n使用两个标签: <object>和<array>, 分别表示对象与数组, 使用name属性表示其名称, 根标签必需为<object>. 如: \n<object>\n    <object name=\"mail\">\n        <object name=\"from\">xxx@xxx.com</object>\n        <object name=\"to\">yyy@yyy.com</object>\n    </object>\n    <array name=\"users\">\n        <object>\n            <object name=\"id\">1</object>\n            <object name=\"name\">james</object>\n        </object>\n        <object>\n            <object name=\"id\">2</object>\n            <object name=\"name\">kent</object>\n        </object>\n    </array>\n</object> \n\n(2)JSON数据格式: \n{mail: {from:\"xxx@xxx.com\", to:\"yyy@yyy.com\"}, users:[{id:1,name:\"james\"},{id:2,name:\"kent\"}]} \n\n(3)Properties数据格式: \nmail.from=xxx@xxx.com\nmail.to=yyy@yyy.com\nusers.0.id=1\nusers.0.name=james\nusers.1.id=2\nusers.1.name=kent");
		JScrollPane pane = new JScrollPane();
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.getViewport().setView(area);
		pane.getViewport().setBackground(Color.white);
		frame.getContentPane().add(pane, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	public static void showException(Exception e) {
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));
		String msg = out.getBuffer().toString();

		CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplateViewer - Exception");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(new BorderLayout());

		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setBackground(Color.WHITE);
		area.setText(msg);
		JScrollPane pane = new JScrollPane();
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.getViewport().setView(area);
		pane.getViewport().setBackground(Color.white);
		frame.getContentPane().add(pane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/*
	private static JFrame frame;

	private static JTextArea area;

	public static void showMessage(String msg) {
		if (frame == null) {
			frame = new JFrame("CommonTemplateViewer - Message");
			frame.setIconImage(ImageFactory.getImage("org/commontemplate/tools/debugger/swing/debug.gif"));
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setSize(800, 600);
			Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension fra = frame.getSize();
			frame.setLocation((scr.width - fra.width) / 2,
					(scr.height - fra.height) / 2);// 在屏幕居中显示
			frame.getRootPane().setFocusable(true);
			frame.getRootPane().setFocusCycleRoot(true);
			frame.getContentPane().setLayout(new BorderLayout());

			area = new JTextArea();
			area.setEditable(false);
			area.setBackground(Color.WHITE);
			JScrollPane pane = new JScrollPane();
			pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			pane.getViewport().setView(area);
			pane.getViewport().setBackground(Color.white);
			frame.getContentPane().add(pane, BorderLayout.CENTER);
		}
		frame.show();
		area.append(msg + "\n");
	}*/

}
