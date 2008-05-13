package org.commontemplate.tools.editor.eclipse;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class TemplateWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
