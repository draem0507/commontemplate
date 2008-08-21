package org.commontemplate.standard.syntax;

public class KeywordsSettings {

	// 特定值关键字

	private String nullKeyword;

	private String trueKeyword;

	private String falseKeyword;

	// 上下文区域信息关键字

	private String currentLocalContextKeyword;

	private String parentLocalContextKeyword;

	private String contextKeyword;

	public String getNull() {
		return nullKeyword;
	}

	public void setNull(String nullKeyword) {
		this.nullKeyword = nullKeyword;
	}

	public String getTrue() {
		return trueKeyword;
	}

	public void setTrue(String trueKeyword) {
		this.trueKeyword = trueKeyword;
	}

	public String getFalse() {
		return falseKeyword;
	}

	public void setFalse(String falseKeyword) {
		this.falseKeyword = falseKeyword;
	}

	public String getCurrentLocalContext() {
		return currentLocalContextKeyword;
	}

	public void setCurrentLocalContext(String currentLocalContextKeyword) {
		this.currentLocalContextKeyword = currentLocalContextKeyword;
	}

	public String getParentLocalContext() {
		return parentLocalContextKeyword;
	}

	public void setParentLocalContext(String parentLocalContextKeyword) {
		this.parentLocalContextKeyword = parentLocalContextKeyword;
	}

	public String getContext() {
		return contextKeyword;
	}

	public void setContext(String contextKeyword) {
		this.contextKeyword = contextKeyword;
	}

}
