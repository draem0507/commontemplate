package org.commontemplate.standard.syntax;

import org.commontemplate.config.Syntax;

public class SyntaxSettings {

	// 指令语法 -----

	private Character directiveLeader;

	private Character expressionBegin;

	private Character expressionEnd;

	// 特殊指令 ------

	private Character lineComment;

	private Character blockComment;

	private Character noParse;

	private String endDirectiveName;

	public Character getDirectiveLeader() {
		return directiveLeader;
	}

	public void setDirectiveLeader(Character directiveLeader) {
		this.directiveLeader = directiveLeader;
	}

	public Character getExpressionBegin() {
		return expressionBegin;
	}

	public void setExpressionBegin(Character expressionBegin) {
		this.expressionBegin = expressionBegin;
	}

	public Character getExpressionEnd() {
		return expressionEnd;
	}

	public void setExpressionEnd(Character expressionEnd) {
		this.expressionEnd = expressionEnd;
	}

	public Character getLineComment() {
		return lineComment;
	}

	public void setLineComment(Character lineComment) {
		this.lineComment = lineComment;
	}

	public Character getBlockComment() {
		return blockComment;
	}

	public void setBlockComment(Character blockComment) {
		this.blockComment = blockComment;
	}

	public Character getNoParse() {
		return noParse;
	}

	public void setNoParse(Character noParse) {
		this.noParse = noParse;
	}

	public String getEndDirectiveName() {
		return endDirectiveName;
	}

	public void setEndDirectiveName(String endDirectiveName) {
		this.endDirectiveName = endDirectiveName;
	}

	public Syntax toSyntax() {
		char leader = getSyntaxSign(this.getDirectiveLeader(), Syntax.DEFAULT_DIRECTIVE_LEADER);
		char expressionBegin = getSyntaxSign(this.getExpressionBegin(), Syntax.DEFAULT_EXPRESSION_BEGIN);
		char expressionEnd = getSyntaxSign(this.getExpressionEnd(), Syntax.DEFAULT_EXPRESSION_END);
		char lineComment = getSyntaxSign(this.getLineComment(), Syntax.DEFAULT_LINE_COMMENT);
		char blockComment = getSyntaxSign(this.getBlockComment(), Syntax.DEFAULT_BLOCK_COMMENT);
		char noParse = getSyntaxSign(this.getNoParse(), Syntax.DEFAULT_NO_PARSE);
		String endDirectiveName = getSyntaxName(this.getEndDirectiveName(), Syntax.DEFAULT_END_DIRECTIVE_NAME);
		return new Syntax(leader, expressionBegin, expressionEnd, lineComment, blockComment, noParse, endDirectiveName);
	}

	private char getSyntaxSign(Character configSign, char defaultSign) {
		if (configSign != null && configSign.charValue() > 0 && configSign.charValue() < 128)
			return configSign.charValue();
		return defaultSign;
	}

	private String getSyntaxName(String configName, String defaultName) {
		if (configName != null && configName.length() > 0)
			return configName;
		return defaultName;
	}

	public static Syntax parseSyntax(String value) {
		value = value.trim();
		char leader = value.length() > 0 ? value.charAt(0) : Syntax.DEFAULT_DIRECTIVE_LEADER;
		char expressionBegin = value.length() > 1 ? value.charAt(1) : Syntax.DEFAULT_EXPRESSION_BEGIN;
		char expressionEnd = value.length() > 2 ? value.charAt(2) : Syntax.DEFAULT_EXPRESSION_END;
		char lineComment = value.length() > 3 ? value.charAt(3) : Syntax.DEFAULT_LINE_COMMENT;
		char blockComment = value.length() > 4 ? value.charAt(4) : Syntax.DEFAULT_BLOCK_COMMENT;
		char noParse = value.length() > 5 ? value.charAt(5) : Syntax.DEFAULT_NO_PARSE;
		String endDirectiveName = value.length() > 6 ? value.substring(6) : Syntax.DEFAULT_END_DIRECTIVE_NAME;
		return new Syntax(leader, expressionBegin, expressionEnd, lineComment, blockComment, noParse, endDirectiveName);
	}

}
