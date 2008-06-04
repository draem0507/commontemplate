package org.commontemplate.tools.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TemplateViewerUI {

	/*static {
		try {
			// 设置swing样式为当前系统风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public static void showSettings() {
		JFrame frame = new JFrame("CommonTemplateViewer - Help");
		/*frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
				TemplateViewerUI.class.getClassLoader().getResource(
						"org/commontemplate/standard/directive/debugdebug/gif")));*/
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
		area.setText("修改*.ctl文件的打开方式为当前commontemplate.exe程序. \n\n模板数据查找方式: 查找与模板同名但不同后缀的数据文件, 如: \n打开test.ctl时, 查找test.xml, test.json, test.properties \n\n(1)XML数据格式: 以data为根节点, 数组名称用下划线\"_\"表示. 如: \n<data>\n    <mail>\n        <from>xxx@xxx.com</from>\n        <to>yyy@yyy.com</to>\n    </mail>\n    <users>\n        <_>\n            <id>1</id>\n            <name>james</name>\n        </_>\n        <_>\n            <id>2</id>\n            <name>kent</name>\n        </_>\n    </users>\n</data> \n\n(2)JSON数据格式: \n{mail: {from:\"xxx@xxx.com\", to:\"yyy@yyy.com\"}, users:[{id:1,name:\"james\"},{id:2,name:\"kent\"}]} \n\n(3)Properties数据格式: \nmail.from=xxx@xxx.com\nmail.to=yyy@yyy.com\nusers.0.id=1\nusers.0.name=james\nusers.1.id=2\nusers.1.name=kent");
		JScrollPane pane = new JScrollPane();
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.getViewport().setView(area);
		pane.getViewport().setBackground(Color.white);
		frame.getContentPane().add(pane, BorderLayout.CENTER);

		frame.show();
	}

	public static void showException(Exception e) {
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));
		String msg = out.getBuffer().toString();

		JFrame frame = new JFrame("CommonTemplateViewer - Exception");
		/*frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
				TemplateViewerUI.class.getClassLoader().getResource(
						"org/commontemplate/standard/directive/debugdebug/gif")));*/
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

		frame.show();
	}
}
