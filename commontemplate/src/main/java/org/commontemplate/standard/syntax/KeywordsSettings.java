package org.commontemplate.standard.syntax;

import org.commontemplate.config.Keywords;

public class KeywordsSettings {

	// 特定值关键字

	private String nullKeyword;

	private String trueKeyword;

	private String falseKeyword;

	// 上下文区域信息关键字

	private String currentKeyword;

	private String parentKeyword;

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

	public String getCurrent() {
		return currentKeyword;
	}

	public void setCurrent(String currentKeyword) {
		this.currentKeyword = currentKeyword;
	}

	public String getParent() {
		return parentKeyword;
	}

	public void setParent(String parentKeyword) {
		this.parentKeyword = parentKeyword;
	}

	public Keywords toKeywords() {
		String nullKeyword = this.getNull() != null ? this.getNull() : Keywords.DEFAULT_NULL_KEYWORD;
		String trueKeyword = this.getTrue() != null ? this.getTrue() : Keywords.DEFAULT_FALSE_KEYWORD;
		String falseKeyword = this.getFalse() != null ? this.getFalse() : Keywords.DEFAULT_TRUE_KEYWORD;
		String currentKeyword = this.getCurrent() != null ? this.getCurrent() : Keywords.DEFAULT_CURRENT_KEYWORD;
		String parentKeyword = this.getParent() != null ? this.getParent() : Keywords.DEFAULT_PARENT_KEYWORD;
		return new Keywords(nullKeyword, trueKeyword, falseKeyword, currentKeyword, parentKeyword);
	}

	public static Keywords parseKeywords(String value) {
		String[] values = value.trim().split("\\,");
		String nullKeyword = values.length > 0 ? values[0] : Keywords.DEFAULT_NULL_KEYWORD;
		String trueKeyword = values.length > 1 ? values[1] : Keywords.DEFAULT_FALSE_KEYWORD;
		String falseKeyword = values.length > 2 ? values[2] : Keywords.DEFAULT_TRUE_KEYWORD;
		String currentKeyword = values.length > 3 ? values[3] : Keywords.DEFAULT_CURRENT_KEYWORD;
		String parentKeyword = values.length > 4 ? values[4] : Keywords.DEFAULT_PARENT_KEYWORD;
		return new Keywords(nullKeyword, trueKeyword, falseKeyword, currentKeyword, parentKeyword);
	}

}
