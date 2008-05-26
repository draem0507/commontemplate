package org.commontemplate.util.swing;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.JTextComponent;

import org.commontemplate.util.I18nMessages;

public class TextPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private TextPopupMenu() {
		super();
	}

	public static void bindEdit(final JTextComponent textComponent) {

	}

	public static void bindCopy(final JTextComponent textComponent) {
		final TextPopupMenu menu = new TextPopupMenu();
		final JMenuItem copyItem = new JMenuItem(I18nMessages.getMessage("TextPopupMenu.copy.menu.item"));
		final JMenuItem copyAllItem = new JMenuItem(I18nMessages.getMessage("TextPopupMenu.copy.all.menu.item"));
		menu.add(copyItem)
			.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					StringSelection stringSelection = new StringSelection(textComponent.getSelectedText());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				}
			});
		menu.add(copyAllItem)
			.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					StringSelection stringSelection = new StringSelection(textComponent.getText());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				}
			});
		textComponent.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent me) {
				 if (me.getModifiers() == MouseEvent.META_MASK) {
					 int x = me.getX();
					 int y = me.getY();
					 String sel = textComponent.getSelectedText();
					 copyItem.setEnabled((sel != null && sel.length() > 0));
					 String all = textComponent.getText();
					 copyAllItem.setEnabled((all != null && all.length() > 0));
					 menu.show(textComponent, x, y);
				 }
			 }
		});
	}

}
