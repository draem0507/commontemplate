package org.commontemplate.tools.debugger.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class TemplateLineMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public TemplateLineMenuItem() {
		super();
	}

	public TemplateLineMenuItem(Action a) {
		super(a);
	}

	public TemplateLineMenuItem(Icon icon) {
		super(icon);
	}

	public TemplateLineMenuItem(String text, Icon icon) {
		super(text, icon);
	}

	public TemplateLineMenuItem(String text, int mnemonic) {
		super(text, mnemonic);
	}

	public TemplateLineMenuItem(String text) {
		super(text);
	}

	private int line = -1;

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

}
