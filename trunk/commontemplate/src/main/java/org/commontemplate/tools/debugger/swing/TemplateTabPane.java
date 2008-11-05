package org.commontemplate.tools.debugger.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
		final JPopupMenu popupMenu = new JPopupMenu();
		final JMenuItem closeActivityItem = new JMenuItem("关闭当前模板(可双击标签关闭)"); // TODO 未国际化
		popupMenu.add(closeActivityItem);
		closeActivityItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				closeActivityTemplate();
			}
		});
		final JMenuItem closeInactivityItem = new JMenuItem("关闭其它模板"); // TODO 未国际化
		popupMenu.add(closeInactivityItem);
		closeInactivityItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				closeInactivityTemplate();
			}
		});
		final JMenuItem closeAllItem = new JMenuItem("关闭所有模板"); // TODO 未国际化
		popupMenu.add(closeAllItem);
		closeAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				closeAllTemplate();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getModifiers() == MouseEvent.META_MASK) {
					int x = e.getX();
					int y = e.getY();
					int selected = getSelectedIndex();
					int count = getTabCount();
					closeActivityItem.setEnabled(selected >= 0);
					closeInactivityItem.setEnabled(selected >= 0 && count > 1);
					closeAllItem.setEnabled(count > 0);
					popupMenu.show(TemplateTabPane.this, x, y);
				} else if (e.getClickCount() == 2) {
					closeActivityTemplate();
				}
			}
		});
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

	public void closeActivityTemplate() {
		int selected = getSelectedIndex();
		if (selected >= 0) {
			removeTabAt(selected);
		}
	}

	public void closeInactivityTemplate() {
		int selected = getSelectedIndex();
		if (selected >= 0) {
			while (getTabCount() > 1) {
				if (getSelectedIndex() == 0) {
					removeTabAt(1);
				} else {
					removeTabAt(0);
				}
			}
		}
	}

	public void closeAllTemplate() {
		while (getTabCount() > 0) {
			removeTabAt(0);
		}
	}

}
