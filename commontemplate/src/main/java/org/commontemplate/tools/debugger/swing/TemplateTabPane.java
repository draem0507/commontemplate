package org.commontemplate.tools.debugger.swing;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.UrlUtils;

public class TemplateTabPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private final JFrame frame;

	public TemplateTabPane(JFrame frame) {
		super();
		this.frame = frame;
	}

	private TemplatePane getTemplatePane(Template template) {
		if (template == null)
			return null;
		for (int i = 0; i < this.getComponentCount(); i ++) {
			TemplatePane templatePane = (TemplatePane)this.getComponent(i);
			if (templatePane != null
					&& template == templatePane.getTemplate()) {
				return templatePane;
			}
		}
		return null;
	}

	public void closeTemplate(Template template) {
		if (template == null)
			return;
		TemplatePane templatePane = getTemplatePane(template);
		if (templatePane != null)
			this.remove(templatePane);
	}

	public TemplatePane addTemplate(Template template) {
		if (template == null)
			return null;
		TemplatePane templatePane = getTemplatePane(template);
		if (templatePane != null) {
			this.setSelectedComponent(templatePane);
		} else {
			templatePane = new TemplatePane(template, frame, this);
			this.addTab(UrlUtils.getFileName(template.getName()), null, templatePane, template.getName());
			this.setSelectedComponent(templatePane);
		}
		return templatePane;
	}

	private Element prevElement;

	public void setElement(Element element) {
		removeElement();
		if (element != null) {
			TemplatePane templatePane = getTemplatePane(element.getTemplate());
			if (templatePane != null) {
				templatePane.setElement(element);
				this.setSelectedComponent(templatePane);
			} else {
				templatePane = addTemplate(element.getTemplate());
				templatePane.setElement(element);
			}
		}
		this.prevElement = element;
	}

	public void removeElement() {
		if (prevElement != null) {
			TemplatePane templatePane = getTemplatePane(prevElement.getTemplate());
			if (templatePane != null)
				templatePane.removeElement();
		}
		this.prevElement = null;
	}

}
