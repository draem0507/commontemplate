package org.commontemplate.config;

/**
 * 表达式求值过程拦截器
 *
 * @author liangfei0201@163.com
 *
 */
public interface EvaluateInterceptor {

	/**
	 * 拦截表达式求值过程
	 *
	 * @param evaluation 表达式求值过程封装
	 */
	Object intercept(Evaluation evaluation);

}
