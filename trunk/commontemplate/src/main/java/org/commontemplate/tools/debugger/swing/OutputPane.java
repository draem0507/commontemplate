package org.commontemplate.tools.debugger.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import org.commontemplate.tools.swing.MenuBuilder;

public class OutputPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	private final JTextArea textArea = new JTextArea();

	private final DefaultHighlighter highlighter = new DefaultHighlighter();

	private final DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode("0xFFCC33"));

	private Object highlight = null;

	public OutputPane() {
		super();
		textArea.setHighlighter(highlighter);
		textArea.setEditable(false);
		textArea.setAutoscrolls(true);
		textArea.setLineWrap(true);
		textArea.setBackground(Color.WHITE);
		textArea.setSize(150, 150);
		JPopupMenu menu = new JPopupMenu();
		MenuBuilder.buildReadonlyTextMenu(textArea, menu);
		menu.addSeparator();
		final JMenuItem clearItem = new JMenuItem("清除"); // TODO 未国际化
		menu.add(clearItem)
			.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					clearText();
				}
			});
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getViewport().setView(textArea);
		this.getViewport().setBackground(Color.WHITE);
		this.getViewport().setAutoscrolls(true);
	}

	public void setText(String source, int begin) {
		if (source == null || source.length() == 0) {
			textArea.setText("");
			return;
		}
		textArea.setText(source);
		int end = textArea.getText().length();
		try {
			if (highlight != null)
				highlighter.removeHighlight(highlight);
			highlight = highlighter.addHighlight(begin, end, painter);
			textArea.select(begin, end);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void clearText() {
		if (highlight != null)
			highlighter.removeHighlight(highlight);
		highlight = null;
		textArea.setText("");
		if (debugOutputFilter != null)
			debugOutputFilter.clearText();
	}

	private DebugOutputFilter debugOutputFilter;

	public DebugOutputFilter getDebugOutputFilter() {
		return debugOutputFilter;
	}

	public void setDebugOutputFilter(DebugOutputFilter debugOutputFilter) {
		if (debugOutputFilter != null)
			debugOutputFilter.showText();
		else
			clearText();
		this.debugOutputFilter = debugOutputFilter;
	}

}
