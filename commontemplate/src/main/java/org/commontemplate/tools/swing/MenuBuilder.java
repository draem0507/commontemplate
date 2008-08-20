package org.commontemplate.tools.swing;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.commontemplate.util.I18nMessages;

public class MenuBuilder {

	private static final long serialVersionUID = 1L;

	private MenuBuilder() {}

	public static JPopupMenu buildReadonlyTextMenu(final JTextComponent component) {
		final JPopupMenu menu = new JPopupMenu();
		buildReadonlyTextMenu(component, menu);
		return menu;
	}

	public static void buildReadonlyTextMenu(final JTextComponent component, final JPopupMenu menu) {
		final JMenuItem copyItem = new JMenuItem(I18nMessages.getMessage("TextPopupMenu.copy.menu.item"));
		menu.add(copyItem)
			.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					StringSelection stringSelection = new StringSelection(component.getSelectedText());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				}
			});
		final JMenuItem copyAllItem = new JMenuItem(I18nMessages.getMessage("TextPopupMenu.copy.all.menu.item"));
		menu.add(copyAllItem)
			.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					StringSelection stringSelection = new StringSelection(component.getText());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				}
			});
		component.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent me) {
				 if (me.getModifiers() == MouseEvent.META_MASK) {
					 int x = me.getX();
					 int y = me.getY();
					 String sel = component.getSelectedText();
					 copyItem.setEnabled((sel != null && sel.length() > 0));
					 String all = component.getText();
					 copyAllItem.setEnabled((all != null && all.length() > 0));
					 menu.show(component, x, y);
				 }
			 }
		});
	}

	public static JPopupMenu buildEditableTextMenu(final JTextComponent component) {
		final JPopupMenu menu = new JPopupMenu();
		buildEditableTextMenu(component, menu);
		return menu;
	}

	public static void buildEditableTextMenu(final JTextComponent component, final JPopupMenu menu) {
		// TODO 构造可编辑框菜单
	}

	public static JPopupMenu buildReadonlyTreeMenu(final JTree component) {
		final JPopupMenu menu = new JPopupMenu();
		buildReadonlyTreeMenu(component, menu);
		return menu;
	}

	public static void buildReadonlyTreeMenu(final JTree component, final JPopupMenu menu) {
		final JMenuItem copyItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.menu.item"));
		menu.add(copyItem);
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TreePath path = component.getSelectionPath();
				if (path != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
							.getLastPathComponent();
					if (node != null) {
						StringSelection stringSelection = new StringSelection(
								String.valueOf(node.getUserObject()));
						Toolkit.getDefaultToolkit().getSystemClipboard()
								.setContents(stringSelection, stringSelection);
					}
				}
			}
		});

		final JMenuItem copyNodeItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.node.menu.item"));
		menu.add(copyNodeItem);
		copyNodeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TreePath path = component.getSelectionPath();
				if (path != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
							.getLastPathComponent();
					if (node != null) {
						StringSelection stringSelection = new StringSelection(
								getAllNode(node));
						Toolkit.getDefaultToolkit().getSystemClipboard()
								.setContents(stringSelection, stringSelection);
					}
				}
			}
		});

		final JMenuItem copyAllItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.all.menu.item"));
		menu.add(copyAllItem);
		copyAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StringSelection stringSelection = new StringSelection(
						getAllNode((DefaultMutableTreeNode) component.getModel().getRoot()));
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
						stringSelection, stringSelection);
			}
		});

		component.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getModifiers() == MouseEvent.META_MASK) {
					int x = me.getX();
					int y = me.getY();
					int selRow = component.getRowForLocation(x, y);
					if (selRow != -1)
						component.setSelectionRow(selRow == -1 ? 0 : selRow);
					copyItem.setEnabled(false);
					TreePath path = component.getSelectionPath();
					if (path != null) {
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
								.getLastPathComponent();
						if (node != null) {
							copyItem.setEnabled(true);
						}
					}
					menu.show(component, x, y);
				}
			}
		});

	}

	private static String getAllNode(DefaultMutableTreeNode node) {
		StringBuffer buf = new StringBuffer();
		buildNode(buf, node, "");
		return buf.toString();
	}

	private static void buildNode(StringBuffer buf, DefaultMutableTreeNode node, String level) {
		if (node != null) {
			buf.append(level + String.valueOf(node.getUserObject()) + "\n");
			if (node.getChildCount() > 0) {
				for (int i = 0; i < node.getChildCount(); i ++) {
					DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getChildAt(i);
					buildNode(buf, child, level + "\t");
				}
			}
		}
	}

	public static JPopupMenu buildEditableTreeMenu(final JTree component) {
		final JPopupMenu menu = new JPopupMenu();
		buildEditableTreeMenu(component, menu);
		return menu;
	}

	public static void buildEditableTreeMenu(final JTree component, final JPopupMenu menu) {
		// TODO 构造可编辑树菜单
	}

	public static JPopupMenu buildReadonlyListMenu(final JList component) {
		final JPopupMenu menu = new JPopupMenu();
		buildReadonlyListMenu(component, menu);
		return menu;
	}

	public static void buildReadonlyListMenu(final JList component, final JPopupMenu menu) {
		final JMenuItem copyItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.menu.item"));
		menu.add(copyItem);
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Object object = component.getSelectedValue();
				if (object != null) {
					StringSelection stringSelection = new StringSelection(object.toString());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
							stringSelection, stringSelection);
				}
			}
		});
		final JMenuItem copyAllItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.all.menu.item"));
		menu.add(copyAllItem);
		copyAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StringBuffer buf = new StringBuffer();
				synchronized (component.getModel()) {
					for (int i = 0; i < component.getModel().getSize(); i ++) {
						try {
							Object object = component.getModel().getElementAt(i);
							if (object != null)
								buf.append(object.toString());
						} catch (Exception e) {
							// ignore
						}
					}
				}
				StringSelection stringSelection = new StringSelection(buf.toString());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
						stringSelection, stringSelection);
			}
		});
		component.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getModifiers() == MouseEvent.META_MASK) {
					int x = me.getX();
					int y = me.getY();
					copyItem.setEnabled(component.getSelectedIndex() != -1);
					copyAllItem.setEnabled(component.getModel().getSize() > 0);
					menu.show(component, x, y);
				}
			}
		});
	}

	public static JPopupMenu buildEditableListMenu(final JList component) {
		final JPopupMenu menu = new JPopupMenu();
		buildEditableListMenu(component, menu);
		return menu;
	}

	public static void buildEditableListMenu(final JList component, final JPopupMenu menu) {
		// TODO 构造可编辑列表菜单
	}

}
