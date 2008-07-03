package org.commontemplate.tools.debugger.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.swing.TextPopupMenu;

/**
 * 模板面板
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplatePane extends JPanel {

	private static final long serialVersionUID = 1L;

	private final TemplateEditorPane editor = new TemplateEditorPane();

	private final JTextField templateNameBox;

	private final JTextField elementBox;

	public TemplatePane() {
		super();
		elementBox = new JTextField();
		TextPopupMenu.bindCopy(elementBox);
		elementBox.setEditable(false);
		elementBox.setBackground(Color.WHITE);

		templateNameBox = new JTextField();
		TextPopupMenu.bindCopy(templateNameBox);
		templateNameBox.setEditable(false);
		templateNameBox.setBackground(Color.WHITE);

		JScrollPane templateBox = new JScrollPane();
		templateBox
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		templateBox.getViewport().setView(editor);
		templateBox.getViewport().setBackground(Color.white);
		templateBox.getViewport().setAutoscrolls(true);
		this.setLayout(new BorderLayout());
		this.add(templateNameBox, BorderLayout.NORTH);
		this.add(templateBox, BorderLayout.CENTER);
		this.add(elementBox, BorderLayout.SOUTH);
	}

	public synchronized Template getTemplate() {
		return editor.getTemplate();
	}

	public synchronized void setTemplate(Template template) {
		if (template != null) {
			templateNameBox.setText(template.getEncoding() + ": "
					+ template.getName());
			editor.setTemplate(template);
		}
	}

	public synchronized Element getElement() {
		return editor.getElement();
	}

	public synchronized void setElement(Element element) {
		if (element != null) {
			Template template = element.getTemplate();
			if (template != null)
				setTemplate(template);
			String elementText = element.getSource();
			elementText = elementText.replaceAll("\n", "\\\\n");
			elementText = elementText.replaceAll("\r", "\\\\r");
			elementText = elementText.replaceAll("\t", "\\\\t");
			elementText = elementText.replaceAll("\f", "\\\\f");
			elementText = elementText.replaceAll("\b", "\\\\b");
			elementBox.setText(element.getType() + ": " + elementText);
			editor.setElement(element);
		}
	}

	public synchronized void removeElement() {
		editor.removeElement();
		elementBox.setText("");
	}

}
