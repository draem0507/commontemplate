package org.commontemplate.standard.syntax;

public class SyntaxSettings {

	// 指令语法 -----

	private String directiveLeader;

	private String expressionBegin;

	private String expressionEnd;

	// 特殊指令 ------

	private String lineComment;

	private String blockComment;

	private String noParse;

	private String endDirectiveName;

	public String getDirectiveLeader() {
		return directiveLeader;
	}

	public void setDirectiveLeader(String directiveLeader) {
		this.directiveLeader = directiveLeader;
	}

	public String getExpressionBegin() {
		return expressionBegin;
	}

	public void setExpressionBegin(String expressionBegin) {
		this.expressionBegin = expressionBegin;
	}

	public String getExpressionEnd() {
		return expressionEnd;
	}

	public void setExpressionEnd(String expressionEnd) {
		this.expressionEnd = expressionEnd;
	}

	public String getLineComment() {
		return lineComment;
	}

	public void setLineComment(String lineComment) {
		this.lineComment = lineComment;
	}

	public String getBlockComment() {
		return blockComment;
	}

	public void setBlockComment(String blockComment) {
		this.blockComment = blockComment;
	}

	public String getNoParse() {
		return noParse;
	}

	public void setNoParse(String noParse) {
		this.noParse = noParse;
	}

	public String getEndDirectiveName() {
		return endDirectiveName;
	}

	public void setEndDirectiveName(String endDirectiveName) {
		this.endDirectiveName = endDirectiveName;
	}

}
