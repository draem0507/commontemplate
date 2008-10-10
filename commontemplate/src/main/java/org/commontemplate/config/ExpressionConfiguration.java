package org.commontemplate.config;

import java.io.Serializable;
import java.util.List;

/**
 * 与表达式相关的配置
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class ExpressionConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取表达式关键字配置
	 *
	 * @return 表达式关键字配置
	 */
	public Keywords getKeywords() {
		return Keywords.DEFAULT;
	}

	/**
	 * 获取操作符处理器决策器
	 *
	 * @return 操作符处理器决策器
	 */
	public abstract OperatorHandlerProvider getOperatorHandlerProvider();

	/**
	 * 是否允许调用函数
	 *
	 * @return true 允许，false 不允许
	 */
	public boolean isFunctionAvailable() {
		return false;
	}

	/**
	 * 获取表达式求值拦截器
	 *
	 * @see org.commontemplate.config.EvaluateInterceptor
	 * @return 表达式求值拦截器, 类型: List&lt;EvaluateInterceptor&gt;
	 */
	public abstract List getEvaluateInterceptors();

	public abstract ExpressionFilter getExpressionFilter();

	/**
	 * 引擎获取配置前的自检验回调.
	 * 子类可以重写此函数进行相应配置有效性检查，如果不需要检查则忽略
	 */
	public void validate() {
		// ignore, empty implement
	}

}
