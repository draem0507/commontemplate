package org.commontemplate.tools.editor.eclipse;

import org.eclipse.jface.text.rules.*;

public class TemplatePartitionScanner extends RuleBasedPartitionScanner {
	public final static String XML_COMMENT = "__xml_comment";
	public final static String XML_TAG = "__xml_tag";
	public final static String CT_DIRECTIVE = "__ct_directive";

	public TemplatePartitionScanner() {

		IToken xmlComment = new Token(XML_COMMENT);
		IToken tag = new Token(XML_TAG);
		IToken directive = new Token(CT_DIRECTIVE);

		IPredicateRule[] rules = new IPredicateRule[3];

		rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[1] = new TagRule(tag);
		rules[2] = new SingleLineRule("$", "[^0-9|_|A-Z|a-z]", directive, '\\');

		setPredicateRules(rules);
	}
}
