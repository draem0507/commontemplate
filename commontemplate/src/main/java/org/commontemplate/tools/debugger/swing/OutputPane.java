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

import org.commontemplate.core.OutputFilter;
import org.commontemplate.tools.swing.MenuBuilder;

public class OutputPane extends JScrollPane implements OutputFilter {

	private static final long serialVersionUID = 1L;

	private final JTextArea textArea = new JTextArea();

	private final DefaultHighlighter highlighter = new DefaultHighlighter();

	private final DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode("0x669900"));

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
		menu.add(new JPopupMenu.Separator());
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

	public void appendText(String source) {
		if (source == null || source.length() == 0)
			return;
		int begin = textArea.getText().length();
		textArea.append(source);
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
	}

	public String filter(String text) {
		appendText(text);
		if (outputFilter != null)
			return outputFilter.filter(text);
		return text;
	}

	private OutputFilter outputFilter;

	public OutputFilter getOutputFilter() {
		return outputFilter;
	}

	public void setOutputFilter(OutputFilter outputFilter) {
		this.outputFilter = outputFilter;
	}

}
