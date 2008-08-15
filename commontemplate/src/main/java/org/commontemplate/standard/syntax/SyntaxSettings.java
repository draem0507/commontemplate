package org.commontemplate.standard.syntax;

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

}
