package org.commontemplate.tools.debugger.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.tools.swing.MenuTextField;

public class PropertiesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final MenuTextField txtTemplateName = new MenuTextField();

	private final MenuTextField txtTemplateEncoding = new MenuTextField();

	private final MenuTextField txtTemplateLength = new MenuTextField();

	private final MenuTextField txtTemplateLastModified = new MenuTextField();

	private final MenuTextField txtElementName = new MenuTextField();

	private final MenuTextField txtElementType = new MenuTextField();

	private final MenuTextField txtElementSource = new MenuTextField();

	private final MenuTextField txtElementLocation = new MenuTextField();

	public PropertiesDialog(JFrame frame) {
		super(frame, true);
		this.setSize(367, 473);
		this.setResizable(false);
		this.setTitle("Properties");
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		JPanel templatePanel = new JPanel();
		templatePanel.setLayout(null);

		JLabel lblTemplateName = new JLabel("Name");
		lblTemplateName.setBounds(20, 20, 80, 24);
		templatePanel.add(lblTemplateName);
		txtTemplateName.setEditable(false);
		txtTemplateName.setBackground(Color.WHITE);
		txtTemplateName.setBounds(100, 20, 200, 24);
		templatePanel.add(txtTemplateName);

		JLabel lblTemplateEncoding = new JLabel("Encoding");
		lblTemplateEncoding.setBounds(20, 80, 80, 24);
		templatePanel.add(lblTemplateEncoding);
		txtTemplateEncoding.setEditable(false);
		txtTemplateEncoding.setBackground(Color.WHITE);
		txtTemplateEncoding.setBounds(100, 80, 200, 24);
		templatePanel.add(txtTemplateEncoding);

		JLabel lblTemplateLength = new JLabel("Length");
		lblTemplateLength.setBounds(20, 140, 80, 24);
		templatePanel.add(lblTemplateLength);
		txtTemplateLength.setEditable(false);
		txtTemplateLength.setBackground(Color.WHITE);
		txtTemplateLength.setBounds(100, 140, 200, 24);
		templatePanel.add(txtTemplateLength);

		JLabel lblTemplateLastModified = new JLabel("LastModified");
		lblTemplateLastModified.setBounds(20, 200, 80, 24);
		templatePanel.add(lblTemplateLastModified);
		txtTemplateLastModified.setEditable(false);
		txtTemplateLastModified.setBackground(Color.WHITE);
		txtTemplateLastModified.setBounds(100, 200, 200, 24);
		templatePanel.add(txtTemplateLastModified);

		JPanel elementPanel = new JPanel();
		elementPanel.setLayout(null);

		JLabel lblElementName = new JLabel("Name");
		lblElementName.setBounds(20, 20, 80, 24);
		elementPanel.add(lblElementName);
		txtElementName.setEditable(false);
		txtElementName.setBackground(Color.WHITE);
		txtElementName.setBounds(100, 20, 200, 24);
		elementPanel.add(txtElementName);

		JLabel lblElementType = new JLabel("Type");
		lblElementType.setBounds(20, 80, 80, 24);
		elementPanel.add(lblElementType);
		txtElementType.setEditable(false);
		txtElementType.setBackground(Color.WHITE);
		txtElementType.setBounds(100, 80, 200, 24);
		elementPanel.add(txtElementType);

		JLabel lblElementSource = new JLabel("Source");
		lblElementSource.setBounds(20, 140, 80, 24);
		elementPanel.add(lblElementSource);
		txtElementSource.setEditable(false);
		txtElementSource.setBackground(Color.WHITE);
		txtElementSource.setBounds(100, 140, 200, 24);
		elementPanel.add(txtElementSource);

		JLabel lblElementLocation = new JLabel("Location");
		lblElementLocation.setBounds(20, 200, 80, 24);
		elementPanel.add(lblElementLocation);
		txtElementLocation.setEditable(false);
		txtElementLocation.setBackground(Color.WHITE);
		txtElementLocation.setBounds(100, 200, 200, 24);
		elementPanel.add(txtElementLocation);

		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setBounds(5, 5, 350, 400);
		tabPane.add("Template", templatePanel);
		tabPane.add("Element", elementPanel);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(295, 412, 60, 24);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PropertiesDialog.this.setVisible(false);
			}
		});

		this.getContentPane().setLayout(null);
		this.getContentPane().add(tabPane);
		this.getContentPane().add(btnOK);
		this.setVisible(false);
	}

	public void showTemplate(Template template, Element element) {
		if (template != null) {
			txtTemplateName.setText(template.getName());
			txtTemplateEncoding.setText(template.getEncoding());
			txtTemplateLength.setText(String.valueOf(template.getLength()));
			txtTemplateLastModified.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(template.getLastModified())));
		} else {
			txtTemplateName.setText("");
			txtTemplateEncoding.setText("");
			txtTemplateLength.setText("");
			txtTemplateLastModified.setText("");
		}
		if (element != null) {
			txtElementName.setText(element.getName());
			txtElementType.setText(element.getType());
			try {
				txtElementSource.setText(escapeElementSource(element.getSource()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			txtElementLocation.setText(String.valueOf(element.getLocation()));
		} else {
			txtElementName.setText("");
			txtElementType.setText("");
			txtElementSource.setText("");
			txtElementLocation.setText("");
		}
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = this.getSize();
		this.setLocation((scr.width - fra.width) / 2, (scr.height - fra.height) / 2);// 在屏幕居中显示
		this.setVisible(true);
	}

	private String escapeElementSource(String elementSource) {
		if (elementSource == null)
			return "";
		elementSource = elementSource.replaceAll("\n", "\\\\n");
		elementSource = elementSource.replaceAll("\r", "\\\\r");
		elementSource = elementSource.replaceAll("\t", "\\\\t");
		elementSource = elementSource.replaceAll("\f", "\\\\f");
		elementSource = elementSource.replaceAll("\b", "\\\\b");
		return elementSource;
	}

}
