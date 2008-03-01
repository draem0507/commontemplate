package org.commontemplate.standard;

import org.commontemplate.config.ExpressionConfiguration;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.util.Assert;

public class ExpressionConfigurationSettings extends ExpressionConfiguration {

	private static final long serialVersionUID = 1L;
	
	private boolean functionAvailable;

	public boolean isFunctionAvailable() {
		return functionAvailable;
	}

	/**
	 * 设置是否允许调用函数，如：${xxx.yyy(zzz)}
	 * 
	 * @param functionAvailable true为允许调用，false为不允许调用
	 */
	public void setFunctionAvailable(boolean functionAvailable) {
		this.functionAvailable = functionAvailable;
	}

	private Keywords keywords = Keywords.DEFAULT;
	
	public Keywords getKeywords() {
		return keywords;
	}
	
	public void setKeywordsSetting(String value) {
		Assert.assertTrue(value != null && value.trim().length() > 0, "keywordsString不能为空！");

		String[] values = value.trim().split("\\,");
		String nullKeyword = values.length > 0 ? values[0] : Keywords.DEFAULT_NULL_KEYWORD;
		String trueKeyword = values.length > 1 ? values[1] : Keywords.DEFAULT_FALSE_KEYWORD;
		String falseKeyword = values.length > 2 ? values[2] : Keywords.DEFAULT_TRUE_KEYWORD;
		String thisKeyword = values.length > 3 ? values[3] : Keywords.DEFAULT_CURRENT_LOCAL_CONTEXT_KEYWORD;
		String superKeyword = values.length > 4 ? values[4] : Keywords.DEFAULT_SUPER_LOCAL_CONTEXT_KEYWORD;
		String contextKeyword = values.length > 5 ? values[5] : Keywords.DEFAULT_CONTEXT_KEYWORD;
		setKeywords(new Keywords(nullKeyword, trueKeyword, falseKeyword, thisKeyword, superKeyword, contextKeyword));
	}
	
	/**
	 * 设置表达式关键字
	 * 
	 * @param keywords 表达式关键字
	 */
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	// 操作符优先级 --------------

	private OperatorHandlerProvider operatorHandlerProvider;
	
	public OperatorHandlerProvider getOperatorHandlerProvider() {
		return operatorHandlerProvider;
	}
	
	/**
	 * 设置操作符供给器
	 * 
	 * @param operatorHandlerProvider 操作符供给器
	 */
	public void setOperatorHandlerProvider(OperatorHandlerProvider operatorHandlerProvider) {
		this.operatorHandlerProvider = operatorHandlerProvider;
	}

}
