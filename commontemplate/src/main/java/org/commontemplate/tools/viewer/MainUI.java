package org.commontemplate.tools.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.commontemplate.tools.swing.CommonTemplateFrame;

public class MainUI {

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
		area.setText("一、运行方式：\n"
				+ "双击*.ctl文件，或右键菜单选择\"CommonTemplate\"打开，即可运行。\n\n"
				+ "二、加载项：\n"
				+ "(1)类：加载安装目录和模板所在目录中的jar包。\n"
				+ "(2)配置：首先查找模板所在目录的commontemplate.properties，\n"
				+ "   再查找安装目录的commontemplate.properties，\n"
				+ "   否则使用默认的org/commontemplate/tools/viewer/commontemplate.properties\n"
				+ "(3)数据：查找与模板同名，以数据格式类型名为后缀的数据文件。\n\n"
				+ "三、数据格式：\n"
				+ "(1)XML数据格式: \n"
				+ "<object>表示对象，<array>表示数组，name属性表示其名称。 注：根标签必需为<object>。如：\n"
				+ "<object>\n"
				+ "    <object name=\"mail\">\n "
				+ "       <object name=\"from\">xxx@xxx.com</object>\n"
				+ "        <object name=\"to\">yyy@yyy.com</object>\n"
				+ "    </object>\n"
				+ "    <array name=\"users\">\n"
				+ "        <object>\n"
				+ "            <object name=\"id\">1</object>\n"
				+ "            <object name=\"name\">james</object>\n"
				+ "        </object>\n"
				+ "        <object>\n"
				+ "            <object name=\"id\">2</object>\n"
				+ "            <object name=\"name\">kent</object>\n"
				+ "        </object>\n"
				+ "    </array>\n"
				+ "</object> \n\n"
				+ "(2)Properties数据格式: \n"
				+ "等号表示键值对，其中，键中的点号表示层级关系，数字表示数组索引。如：\n"
				+ "mail.from=xxx@xxx.com\n"
				+ "mail.to=yyy@yyy.com\n"
				+ "users.0.id=1\nusers.0.name=james\n"
				+ "users.1.id=2\nusers.1.name=kent \n\n"
				+ "(3)JSON数据格式: \n"
				+ "大括号表示对象，冒号表示属性，方括号表示数组，逗号表示项，引号表示字符串。如：\n"
				+ "{mail: {from:\"xxx@xxx.com\", to:\"yyy@yyy.com\"}, users:[{id:1,name:\"james\"},{id:2,name:\"kent\"}]} \n\n"
				+ "(4)YAML数据格式: \n"
				+ "冒号表示对象属性，横线表示数组项，引号表示字符串。如：\n"
				+ "mail:\n"
				+ "    from: \"xxx@xxx.com\"\n"
				+ "    to: \"yyy@yyy.com\"\n"
				+ "users:\n"
				+ "    - id: 1\n"
				+ "      name: \"james\"\n"
				+ "    - id: 2\n"
				+ "      name: \"kent\"\n");
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

	public static void choosePath(String defaultPath) {
		CommonTemplateFrame frame = new CommonTemplateFrame();
		frame.setTitle("CommonTemplateViewer - Choose");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);

		final JTextField fileField = new JTextField(defaultPath);
		fileField.setBounds(20, 20, 300, 24);
		JButton browseButton = new JButton("浏览"); // TODO 未国际化
		browseButton.setBounds(240, 20, 80, 24);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser(fileField.getText()); //以上次打开的文件为默认路径打开文件选取择框图
				fc.setDialogTitle("请选择模板生成目标位置"); // TODO 未国际化
				int ch = fc.showOpenDialog(null);
				if (ch == JFileChooser.APPROVE_OPTION)
					fileField.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});
		JButton chooseButton = new JButton("选择"); // TODO 未国际化
		chooseButton.setBounds(340, 20, 80, 24);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser(fileField.getText()); //以上次打开的文件为默认路径打开文件选取择框图
				fc.setDialogTitle("请选择模板生成目标位置"); // TODO 未国际化
				int ch = fc.showOpenDialog(null);
				if (ch == JFileChooser.APPROVE_OPTION)
					fileField.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});

		frame.getContentPane().add(fileField);
		frame.getContentPane().add(chooseButton);
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
