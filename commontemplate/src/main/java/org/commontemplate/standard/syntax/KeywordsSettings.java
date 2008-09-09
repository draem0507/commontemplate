package org.commontemplate.standard.syntax;

import org.commontemplate.config.Keywords;

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

	public Keywords toKeywords() {
		String nullKeyword = this.getNull() != null ? this.getNull() : Keywords.DEFAULT_NULL_KEYWORD;
		String trueKeyword = this.getTrue() != null ? this.getTrue() : Keywords.DEFAULT_FALSE_KEYWORD;
		String falseKeyword = this.getFalse() != null ? this.getFalse() : Keywords.DEFAULT_TRUE_KEYWORD;
		String currentLocalContextKeyword = this.getCurrentLocalContext() != null ? this.getCurrentLocalContext() : Keywords.DEFAULT_CURRENT_LOCAL_CONTEXT_KEYWORD;
		String parentLocalContextKeyword = this.getParentLocalContext() != null ? this.getParentLocalContext() : Keywords.DEFAULT_PARENT_LOCAL_CONTEXT_KEYWORD;
		String contextKeyword = this.getContext() != null ? this.getContext() : Keywords.DEFAULT_CONTEXT_KEYWORD;
		return new Keywords(nullKeyword, trueKeyword, falseKeyword, currentLocalContextKeyword, parentLocalContextKeyword, contextKeyword);
	}

	public static Keywords parseKeywords(String value) {
		String[] values = value.trim().split("\\,");
		String nullKeyword = values.length > 0 ? values[0] : Keywords.DEFAULT_NULL_KEYWORD;
		String trueKeyword = values.length > 1 ? values[1] : Keywords.DEFAULT_FALSE_KEYWORD;
		String falseKeyword = values.length > 2 ? values[2] : Keywords.DEFAULT_TRUE_KEYWORD;
		String thisKeyword = values.length > 3 ? values[3] : Keywords.DEFAULT_CURRENT_LOCAL_CONTEXT_KEYWORD;
		String superKeyword = values.length > 4 ? values[4] : Keywords.DEFAULT_PARENT_LOCAL_CONTEXT_KEYWORD;
		String contextKeyword = values.length > 5 ? values[5] : Keywords.DEFAULT_CONTEXT_KEYWORD;
		return new Keywords(nullKeyword, trueKeyword, falseKeyword, thisKeyword, superKeyword, contextKeyword);
	}

}
