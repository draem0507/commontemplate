package org.commontemplate.tools.debugger.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.commontemplate.core.Element;
import org.commontemplate.core.Template;

/**
 * 模板面板
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplatePane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	private final TemplateEditorPane editor;

	public TemplatePane(JFrame frame) {
		super();
		editor = new TemplateEditorPane(frame);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getViewport().setView(editor);
		this.getViewport().setBackground(Color.white);
		this.getViewport().setAutoscrolls(true);
	}

	public Element getElement() {
		return editor.getElement();
	}

	public Template getTemplate() {
		return editor.getTemplate();
	}

	public void setElement(Element element) {
		editor.setElement(element);
	}

	public void setTemplate(Template template) {
		editor.setTemplate(template);
	}

	public void removeElement() {
		editor.removeElement();
	}

}
