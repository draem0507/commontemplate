package org.commontemplate.tools.swing;

import javax.swing.JTextArea;
import javax.swing.text.Document;

public class MenuTextArea extends JTextArea {

	private static final long serialVersionUID = 1L;

	public MenuTextArea() {
		super();
		init();
	}

	public MenuTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		init();
	}

	public MenuTextArea(Document doc) {
		super(doc);
		init();
	}

	public MenuTextArea(int rows, int columns) {
		super(rows, columns);
		init();
	}

	public MenuTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		init();
	}

	public MenuTextArea(String text) {
		super(text);
		init();
	}

	private void init() {
		MenuBuilder.buildReadonlyTextMenu(this);
	}

}
