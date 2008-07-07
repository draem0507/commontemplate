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

import org.commontemplate.util.I18nMessages;

public class MenuBuilder {

	private static final long serialVersionUID = 1L;

	private MenuBuilder() {}

	public static void buildReadonlyTextMenu(final JTextComponent component) {
		final JPopupMenu menu = new JPopupMenu();
		buildReadonlyTextMenu(component, menu);
	}

	public static void buildReadonlyTextMenu(final JTextComponent component, final JPopupMenu menu) {
		final JMenuItem copyItem = new JMenuItem(I18nMessages.getMessage("TextPopupMenu.copy.menu.item"));
		final JMenuItem copyAllItem = new JMenuItem(I18nMessages.getMessage("TextPopupMenu.copy.all.menu.item"));
		menu.add(copyItem)
			.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					StringSelection stringSelection = new StringSelection(component.getSelectedText());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				}
			});
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

	public static void buildEditableTextMenu(final JTextComponent component) {
		final JPopupMenu menu = new JPopupMenu();
		buildEditableTextMenu(component, menu);
	}

	public static void buildEditableTextMenu(final JTextComponent component, final JPopupMenu menu) {
		// TODO 构造可编辑框菜单
	}

	public static void buildReadonlyTreeMenu(final JTree component) {
		final JPopupMenu menu = new JPopupMenu();
		buildReadonlyTreeMenu(component, menu);
	}

	public static void buildReadonlyTreeMenu(final JTree component, final JPopupMenu menu) {
		// TODO 构造只读树菜单
	}

	public static void buildEditableTreeMenu(final JTree component) {
		final JPopupMenu menu = new JPopupMenu();
		buildEditableTreeMenu(component, menu);
	}

	public static void buildEditableTreeMenu(final JTree component, final JPopupMenu menu) {
		// TODO 构造可编辑树菜单
	}

	public static void buildReadonlyListMenu(final JList component) {
		final JPopupMenu menu = new JPopupMenu();
		buildReadonlyListMenu(component, menu);
	}

	public static void buildReadonlyListMenu(final JList component, final JPopupMenu menu) {
		// TODO 构造只读列表菜单
	}

	public static void buildEditableListMenu(final JList component) {
		final JPopupMenu menu = new JPopupMenu();
		buildEditableListMenu(component, menu);
	}

	public static void buildEditableListMenu(final JList component, final JPopupMenu menu) {
		// TODO 构造可编辑列表菜单
	}

}
