package org.commontemplate.tools.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public MyMenuItem() {
		super();
	}

	public MyMenuItem(Action a) {
		super(a);
	}

	public MyMenuItem(Icon icon) {
		super(icon);
	}

	public MyMenuItem(String text, Icon icon) {
		super(text, icon);
	}

	public MyMenuItem(String text, int mnemonic) {
		super(text, mnemonic);
	}

	public MyMenuItem(String text) {
		super(text);
	}

	public void checkShow(JComponent component, JPopupMenu menu, int x, int y) {
		this.setEnabled(isShow(component, menu, x, y));
	}

	protected boolean isShow(JComponent component, JPopupMenu menu, int x, int y) {
		return true;
	}

}
