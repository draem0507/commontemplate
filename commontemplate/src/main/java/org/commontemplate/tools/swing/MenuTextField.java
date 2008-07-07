package org.commontemplate.tools.swing;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class MenuTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public MenuTextField() {
		super();
		init();
	}

	public MenuTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		init();
	}

	public MenuTextField(int columns) {
		super(columns);
		init();
	}

	public MenuTextField(String text, int columns) {
		super(text, columns);
		init();
	}

	public MenuTextField(String text) {
		super(text);
		init();
	}

	private void init() {
		MenuBuilder.buildReadonlyTextMenu(this);
	}

}
