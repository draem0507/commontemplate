package org.commontemplate.tools.editor.eclipse;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class TemplateTagScanner extends RuleBasedScanner {

	public TemplateTagScanner(ColorManager manager) {
		IToken string =
			new Token(
				new TextAttribute(manager.getColor(ITemplateColorConstants.STRING)));
		IToken directive =
			new Token(
				new TextAttribute(manager.getColor(ITemplateColorConstants.DIRECTIVE)));

		IRule[] rules = new IRule[4];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new TemplateWhitespaceDetector());
		rules[3] = new SingleLineRule("$", "[^(0-9|_|A-Z|a-z)]", directive, '\\');
		
		setRules(rules);
	}
}
