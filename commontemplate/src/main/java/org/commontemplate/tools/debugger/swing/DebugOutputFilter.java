package org.commontemplate.tools.debugger.swing;

import java.io.Serializable;

import javax.swing.ButtonModel;

import org.commontemplate.core.OutputFilter;

public class DebugOutputFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private final OutputPane outputPane;

	private final ButtonModel buttonModel;

	private StringBuffer buffer = new StringBuffer();

	private int last = 0;

	public DebugOutputFilter(OutputPane outputPane, ButtonModel buttonModel) {
		this.outputPane = outputPane;
		this.buttonModel = buttonModel;
	}

	public String filter(String text) {
		if (outputPane.getDebugOutputFilter() == this
				&& buttonModel.isSelected()) {
			last = buffer.length();
			buffer.append(text);
			outputPane.setText(buffer.toString(), last);
		}
		if (outputFilter != null)
			return outputFilter.filter(text);
		return text;
	}

	public void showText() {
		outputPane.setText(buffer.toString(), last);
	}

	public void clearText() {
		buffer.setLength(0);
		last = 0;
	}

	private OutputFilter outputFilter;

	public OutputFilter getOutputFilter() {
		return outputFilter;
	}

	public void setOutputFilter(OutputFilter outputFilter) {
		this.outputFilter = outputFilter;
	}

}
