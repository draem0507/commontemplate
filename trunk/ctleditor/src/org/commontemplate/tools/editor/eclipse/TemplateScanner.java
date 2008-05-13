package org.commontemplate.tools.editor.eclipse;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class TemplateScanner extends RuleBasedScanner {

	public TemplateScanner(ColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(ITemplateColorConstants.PROC_INSTR)));
		
		IToken directive =
			new Token(
				new TextAttribute(
					manager.getColor(ITemplateColorConstants.STRING)));
		
		IRule[] rules = new IRule[3];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new TemplateWhitespaceDetector());
		rules[2] = new SingleLineRule("$", "[^0-9|_|A-Z|a-z]", directive, '\\');
		
		setRules(rules);
	}
}
