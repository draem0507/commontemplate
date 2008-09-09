package org.commontemplate.standard;

import java.util.List;

import org.commontemplate.config.ExpressionConfiguration;
import org.commontemplate.config.ExpressionFilter;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.syntax.KeywordsSettings;

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

	/**
	 * 设置表达式关键字
	 *
	 * @param keywords 表达式关键字
	 */
	public void setKeywords(Keywords keywords) {
		if (keywords == null)
			return;
		this.keywords = keywords;
	}

	public void setKeywordsSettings(KeywordsSettings settings) {
		// Assert.assertNotNull(settings, "ConfigurationSettings.keywords.string.required");
		if (settings == null)
			return;
		this.keywords = settings.toKeywords();
	}

	public void setKeywordsString(String value) {
		// Assert.assertTrue(value != null && value.trim().length() > 0, "ExpressionConfigurationSettings.keywords.string.required");
		if (value == null)
			return;
		this.keywords = KeywordsSettings.parseKeywords(value);
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

	private List evaluateInterceptors;

	public List getEvaluateInterceptors() {
		return evaluateInterceptors;
	}

	public void setEvaluateInterceptors(List evaluateInterceptors) {
		this.evaluateInterceptors = evaluateInterceptors;
	}

	private ExpressionFilter expressionFilter;

	public ExpressionFilter getExpressionFilter() {
		return expressionFilter;
	}

	public void setExpressionFilter(ExpressionFilter expressionFilter) {
		this.expressionFilter = expressionFilter;
	}

}
